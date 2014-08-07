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

  def toTrailingConditionalImpl[A: c.WeakTypeTag](c: Context)(f: c.Expr[A]): c.Expr[TrailingConditional[A]] = {
    import c.universe._
    val resultType = implicitly[c.WeakTypeTag[A]].tpe
    val tree =
      q"""
         new TrailingConditional[${tq"$resultType"}] {
           def when(p: Boolean) = scala.ext.UnlessWhen.when(p)($f)
           def unless(p: Boolean) = scala.ext.UnlessWhen.unless(p)($f)
         }
       """
    c.Expr[TrailingConditional[A]](tree)
  }

}


