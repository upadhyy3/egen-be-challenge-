import static spark.Spark.*;

public class MainApplication {

	public static void main(String[] args) {

		/**
		 * Model class has Post and Get method for the application
		 */
		Model model = new Model();

		/**
		 * Data Access Object creates and maintains connection with MongoDB
		 */
		DAO databaseObject = new DAO();

		/**
		 * calls postCreateUser method of Model class to create new user
		 * 
		 * @return id of the created user and response -> 201 (Created) || 409
		 *         (for conflict)
		 */

		post("/createUser", (request, response) -> {
			return model.postCreateNewUser(request, response, databaseObject);
		});

		/**
		 * calls postUpdateUser method of Model class to update existing user
		 * 
		 * @return _id of updated user and response -> 201 (Created) || 406 (for
		 *         NA)
		 */
		post("/updateUser", (request, response) -> {
			return model.postUpdateUser(request, response, databaseObject);
		});

		/**
		 * calls responseGetAllUsers method of Model class to get all new user
		 * 
		 * @return _id of updated user and response -> 200(OK)
		 */

		get("/getAllUsers", (request, response) -> {
			return model.reponseGetAllUser(request, response, databaseObject);
		});
	}
}
