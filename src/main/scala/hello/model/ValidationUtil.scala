package hello.model

import scalaz.syntax.ApplyOps
import scalaz.{ Success, Failure }

trait ValidationUtil {

  def validateInt(params: Map[String, String], key: String, displayName: String) =
    (params.get(key) match {
      case Some(v) => try { Success(v.toInt) } catch {
        case e: NumberFormatException =>
          Failure(s"ERROR: $displayName が整数ではありません。")
      }
      case None => Failure(s"ERROR: $displayName が指定されていません。")
    }).toValidationNel

  def validateNonEmptyString(params: Map[String, String], key: String, displayName: String) =
    (params.get(key) match {
      case Some(v) =>
        if (!v.isEmpty()) Success(v)
        else Failure(s"ERROR: $displayName が空欄です。")
      case None => Failure(s"ERROR: $displayName が指定されていません。")
    }).toValidationNel
}
