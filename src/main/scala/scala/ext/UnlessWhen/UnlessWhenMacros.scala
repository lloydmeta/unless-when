package scala.ext.UnlessWhen

import scala.reflect.macros._

/**
 * Created by Lloyd on 8/7/14.
 */
object UnlessWhenMacros {

  def whenImp[A: c.WeakTypeTag](c: Context)(p: c.Expr[Boolean])(f: c.Expr[A]): c.Expr[Option[A]] = {
    import c.universe._
    c.Expr[Option[A]](q"if ($p) Some($f) else None")
  }

  def unlessImp[A: c.WeakTypeTag](c: Context)(p: c.Expr[Boolean])(f: c.Expr[A]): c.Expr[Option[A]] = {
    import c.universe._
    c.Expr[Option[A]](q"if (!$p) Some($f) else None")
  }

  def toTrailingWhenImpl[A: c.WeakTypeTag](c: Context)(f: c.Expr[A]): c.Expr[TrailingWhen[A]] = {
    import c.universe._
    val resultType = implicitly[c.WeakTypeTag[A]].tpe
    val tree =
      q"""
         new TrailingWhen[${tq"$resultType"}] {
           def when(p: Boolean) = scala.ext.UnlessWhen.when(p)($f)
         }
       """
    c.Expr[TrailingWhen[A]](tree)
  }

  def toTrailingUnlessImpl[A: c.WeakTypeTag](c: Context)(f: c.Expr[A]): c.Expr[TrailingUnless[A]] = {
    import c.universe._
    val resultType = implicitly[c.WeakTypeTag[A]].tpe
    val tree =
      q"""
         new TrailingUnless[${tq"$resultType"}] {
           def unless(p: Boolean) = scala.ext.UnlessWhen.unless(p)($f)
         }
       """
    c.Expr[TrailingUnless[A]](tree)
  }
}


