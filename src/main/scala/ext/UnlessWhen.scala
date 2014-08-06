package scala.ext

import scala.language.experimental.macros
import scala.reflect.macros.Context

/**
 * Import the unless and when methods from here.
 */
object UnlessWhen {
  /**
   * Runs the function if the given predicate evaluates to true. Returns None if the predicate returns
   * false or the result of the function wrapped in a Some if it returns true.
   *
   * This is a macro expansion, so the function provided is evaluated "called lazily"
   *
   * @param p precedent to test with
   * @param f function to compute
   */
  def when[A](p: Boolean)(f: A): Option[A] = macro whenImp[A]

  /**
   * Runs the function if the given predicate evaluates to false. Returns None if the predicate returns
   * false or the result of the function wrapped in a Some if it returns true.
   *
   * This is a macro expansion, so the function provided is evaluated "called lazily"
   *
   * @param p precedent to test with
   * @param f function to compute
   */
  def unless[A](p: Boolean)(f: A): Option[A] = macro unlessImp[A]

  def whenImp[A: c.WeakTypeTag](c: Context)(p: c.Expr[Boolean])(f: c.Expr[A]): c.Expr[Option[A]] = {
    import c.universe._
    c.Expr[Option[A]](q"if ($p) Some($f) else None")
  }

  def unlessImp[A: c.WeakTypeTag](c: Context)(p: c.Expr[Boolean])(f: c.Expr[A]): c.Expr[Option[A]] = {
    import c.universe._
    c.Expr[Option[A]](q"if (!$p) Some($f) else None")
  }
}
