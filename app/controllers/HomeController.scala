
package controllers

import javax.inject._

import models.Employee
import play.api.data.Forms._
import play.api.data._
import play.api.mvc._
import play.api.i18n.Messages.Implicits._
import play.api.Play.current
/**
 *
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject() extends Controller {

  def index = Action {
    Ok(views.html.index(loginForm))
  }
  
  def about = Action {
    Ok(views.html.about())
  }

  def doLogin = Action { implicit request =>
    loginForm.bindFromRequest.fold(
        formWithErrors => BadRequest(views.html.index(formWithErrors)),
        employee => Ok(views.html.submitted(employee)))
  }

  def loginForm = Form(
    mapping(
      "Employee Name" -> nonEmptyText,
      "Age" -> number(min = 16,max = 60),
      "Sex" -> optional(text),
      "Address" -> nonEmptyText,
      "Email" -> email,
      "Education" -> nonEmptyText,
      "Marital Status" ->optional(text),
      "Salary (in K)" -> number(min = 10 , max = 100)
    )(Employee.apply)(Employee.unapply)
  )

}
 
