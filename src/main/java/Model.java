import java.io.IOException;
import java.util.UUID;

import spark.Request;
import spark.Response;
import json.Address;
import json.Company;
import json.User;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Model {

	/**
	 * helper method for postCreateNewUser, creates POJO
	 * 
	 * @Parameter user attributes
	 * @return _id
	 */
	public static String createUser(String firstName, String lastName,
			String email, Address address, String dateCreated, Company company,
			String profilePic) {

		User post = new User();
		UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d");
		String id = UUID.randomUUID().toString();
		post.setId(id);
		post.setFirstName(firstName);
		post.setLastName(lastName);
		post.setEmail(email);
		post.setAddress(address);
		post.setDateCreated(dateCreated);
		post.setCompany(company);
		post.setProfilePic(profilePic);
		return id;
	}

	/**
	 * gets all the Documents from mongoDB, calls helper method from DOA class
	 * getAllUsers
	 * 
	 * @Parameter HTTP Request , HTTP Response , DOA object for data persistence
	 * @return json String of all existing users
	 */
	public String reponseGetAllUser(Request request, Response response,
			DAO databaseObject) {
		response.status(200);
		response.type("application/json");
		return databaseObject.getAllUsers();
	}

	/**
	 * create new user from input json request object
	 * 
	 * @Parameter HTTP Request , HTTP Response , DOA object for data persistence
	 * @return _id and response
	 */
	public String postCreateNewUser(Request request, Response response,
			DAO databaseObject)
			throws com.fasterxml.jackson.core.JsonParseException,
			JsonMappingException, IOException {
		try {
			String id = "";
			ObjectMapper mapper = new ObjectMapper();
			User creation = mapper.readValue(request.body(), User.class);
			// check is user already exists
			if (!databaseObject.userExist(creation)) {
				id = Model.createUser(creation.getFirstName(),
						creation.getLastName(), creation.getEmail(),
						creation.getAddress(), creation.getDateCreated(),
						creation.getCompany(), creation.getProfilePic());
				creation.setId(id);
				response.status(201);
				response.type("application/json");
				databaseObject.storeInDatabase(creation);
			} else {
				response.status(409);
			}
			return id;
		} catch (JsonParseException jpe) {
			response.status(404);
			return "";
		}
	}

	/**
	 * update existing user from input json request object
	 * 
	 * @Parameter HTTP Request , HTTP Response , DOA object for data persistence
	 * @return _id and response
	 */
	public String postUpdateUser(Request request, Response response,
			DAO databaseObject)
			throws com.fasterxml.jackson.core.JsonParseException,
			JsonMappingException, IOException {
		try {
			String id = "";
			ObjectMapper mapper = new ObjectMapper();
			User creation = mapper.readValue(request.body(), User.class);
			// check is user already exists
			if (databaseObject.userExist(creation)) {
				databaseObject.UpdateUser(creation);
				response.status(202);
				response.type("application/json");
			} else {
				response.status(406);
			}
			return id;
		} catch (JsonParseException jpe) {
			response.status(404);
			return "";
		}
	}
}
