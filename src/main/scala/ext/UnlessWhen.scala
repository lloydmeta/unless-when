package scala.ext

import scala.language.experimental.macros


/**
 * Import the unless and when methods from here.
 */
object UnlessWhenTrailing {

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

  implicit def toTrailingConditional[A](f: => A) = new TrailingConditional(f)

}

sealed class TrailingConditional[A](f: => A) {
  /**
   * Returns None if followed by a false-y expression otherwise
   * returns the preceding expression in Some
   */
  def when(p: Boolean) = UnlessWhenTrailing.when(p)(f)

  /**
   * Returns None if followed by a truth-y expression otherwise
   * returns the preceding expression in Some
   */
  def unless(p: Boolean) = UnlessWhenTrailing.unless(p)(f)
}