package scala.ext

import scala.reflect.macros._

object UnlessWhenMacros {

  def whenImp[A: c.WeakTypeTag](c: Context)(p: c.Expr[Boolean])(f: c.Expr[A]): c.Expr[Option[A]] = {
    import c.universe._
    c.Expr[Option[A]](q"if ($p) Some($f) else None")
  }

  def unlessImp[A: c.WeakTypeTag](c: Context)(p: c.Expr[Boolean])(f: c.Expr[A]): c.Expr[Option[A]] = {
    import c.universe._
    c.Expr[Option[A]](q"if (!$p) Some($f) else None")
  }
}
