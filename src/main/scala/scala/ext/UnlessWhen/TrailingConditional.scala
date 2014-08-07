package scala.ext.UnlessWhen

/**
 * Created by Lloyd on 8/7/14.
 */
/**
 * Helper trait for supporting trailing conditionals.
 */
trait TrailingConditional[A] {
  /**
   * Returns None if followed by a false-y expression otherwise
   * returns the preceding expression in Some
   *
   * Note that the preceding expression will be computed lazily depending
   * on the outcome of the predicate.
   */
  def when(p: Boolean): Option[A]

  /**
   * Returns None if followed by a truth-y expression otherwise
   * returns the preceding expression in Some.
   *
   * Note that the preceding expression will be computed lazily depending
   * on the outcome of the predicate.
   */
  def unless(p: Boolean): Option[A]
}