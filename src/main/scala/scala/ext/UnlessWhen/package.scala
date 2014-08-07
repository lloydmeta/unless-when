package scala.ext

import scala.language.experimental.macros
/**
 * Created by Lloyd on 8/7/14.
 */

/**
 * Import this package to get unless and when support in your code
 *
 * {{{
 * import scala.ext.UnlessWhen._
 * }}}
 */
package object UnlessWhen {

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
  implicit def toTrailingConditional[A](f: A): TrailingConditional[A] = macro UnlessWhenMacros.toTrailingConditionalImpl[A]

}
