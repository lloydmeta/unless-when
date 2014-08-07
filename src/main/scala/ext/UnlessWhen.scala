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
  def when[A](p: Boolean)(f: A): Option[A] = macro UnlessWhenMacros.whenImp[A]

  /**
   * Runs the function if the given predicate evaluates to false. Returns None if the predicate returns
   * false or the result of the function wrapped in a Some if it returns true.
   *
   * This is a macro expansion, so the function provided is evaluated "called lazily"
   *
   * @param p precedent to test with
   * @param f function to compute
   */
  def unless[A](p: Boolean)(f: A): Option[A] = macro UnlessWhenMacros.unlessImp[A]

  /**
   * Implicit conversion to a TrailingConditional[A] handled by a macro
   *
   * Note that since the conversion is delegated to a macro, the computation of the result is
   * lazy (and won't happen unless the predicate satisfies unless/when semantics)
   */
  implicit def toTrailingConditional[A](f: A): scala.ext.UnlessWhen.TrailingConditional[A] = macro toTrailingConditionalImpl[A]

  def toTrailingConditionalImpl[A: c.WeakTypeTag](c: Context)(f: c.Expr[A]): c.Expr[scala.ext.UnlessWhen.TrailingConditional[A]] = {
    import c.universe._
    val resultType = implicitly[c.WeakTypeTag[A]].tpe
    val tree =
      q"""
         new TrailingConditional[${tq"$resultType"}] {
           def when(p: Boolean) = UnlessWhen.when(p)($f)
           def unless(p: Boolean) = UnlessWhen.unless(p)($f)
         }
       """
    c.Expr[TrailingConditional[A]](tree)
  }

  /**
   * Helper trait for supporting trailing conditionals.
   */
  trait TrailingConditional[A] {
    /**
     * Returns None if followed by a false-y expression otherwise
     * returns the preceding expression in Some
     */
    def when(p: Boolean): Option[A]

    /**
     * Returns None if followed by a truth-y expression otherwise
     * returns the preceding expression in Some
     */
    def unless(p: Boolean): Option[A]
  }

}


