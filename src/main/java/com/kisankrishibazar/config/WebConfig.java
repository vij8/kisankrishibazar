package com.kisankrishibazar.config;

import static spark.Spark.before;
import static spark.Spark.get;
import static spark.Spark.halt;
import static spark.Spark.post;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.eclipse.jetty.util.MultiMap;
import org.eclipse.jetty.util.UrlEncoded;

import spark.ModelAndView;
import spark.Request;
import spark.Spark;
import spark.template.freemarker.FreeMarkerEngine;

import com.kisankrishibazar.dao.LoginDAO;
import com.kisankrishibazar.dao.RetailerDAO;
import com.kisankrishibazar.model.JsonTransformer;
import com.kisankrishibazar.model.OrderAvailable;
import com.kisankrishibazar.model.User;
import com.kisankrishibazar.model.OrderHistory;
import com.kisankrishibazar.services.impl.KisankrishiServices;

public class WebConfig
{
	private KisankrishiServices kisanKrishiServices;

	private static final String USER_SESSION_ID = "user";

	private LoginDAO dao;
	private RetailerDAO retailerDao;

	public WebConfig(KisankrishiServices service, Map<String, Object> map)
	{
		this.kisanKrishiServices = service;
		this.dao = (LoginDAO) map.get("loginDao");
		Spark.staticFileLocation("/public");
		setupFarmerRoutes();
		setupReatilerRoutes();
	}

	private void setupFarmerRoutes()
	{

		get("/farmer", (req, res) -> "hi I am farmer");

	}

	private void setupReatilerRoutes()
	{
		get("/retailer/home	", (req, res) -> {
			Map<String, Object> map = new HashMap<>();
			return new ModelAndView(map, "/login.ftl");
		}, new FreeMarkerEngine());
		
		
		get("/retailer/dashboard", (req, res) -> {
			Map<String, Object> map = new HashMap<>();
			return new ModelAndView(map, "/dashboard.ftl");
		}, new FreeMarkerEngine());
		

		post("/retailer/login", (req, res) -> {

			Map<String, Object> map = new HashMap<>();
			User user = new User();
			try {
				MultiMap<String> params = new MultiMap<String>();
				UrlEncoded.decodeTo(req.body(), params, "UTF-8");
				BeanUtils.populate(user, params);
			}
			catch (Exception e) {
				halt(501);
				return null;
			}
			// LoginResult result = service.checkUser(user);

				if (true) {
					addAuthenticatedUser(req, user);
					// res.redirect("/");
					// halt();
			}
			else {
				map.put("error", "error");
			}
			map.put("username", user.getUsername());
			return dao.getUserDetail(user.getUsername(), user.getPassword());
		}, new JsonTransformer());
		/*
		 * Checks if the user is already authenticated
		 */
		before("/retailer/login", (req, res) -> {
			User authUser = getAuthenticatedUser(req);
			if (authUser != null) {
				res.redirect("/");
				halt();
			}
		});
		/*
		 * Register new User
		 */
		post("/retailer/register", (req, res) -> {
			User user = new User();
			try {
				MultiMap<String> params = new MultiMap<String>();
				UrlEncoded.decodeTo(req.body(), params, "UTF-8");
				BeanUtils.populate(user, params);
			}
			catch (Exception e) {
				halt(501);
				return null;
			}
			Boolean result = retailerDao.registerNewUser(user);
			if (result) {
				return "SUCCESS";
			}
			else {
				return "FAILURE";
			}
		}, new JsonTransformer());
		/*
		 * To fetch orders Available
		 */
		get("/retailer/orderAvailable", (req, res) -> {
			OrderAvailable orderAvailable = new OrderAvailable();
			try {
				MultiMap<String> params = new MultiMap<String>();
				UrlEncoded.decodeTo(req.body(), params, "UTF-8");
				BeanUtils.populate(orderAvailable, params);
			}
			catch (Exception e) {
				halt(501);
				return null;
			}
			return retailerDao.getOrderAvailable(orderAvailable);
		}, new JsonTransformer());

		get("/retailer/orderHistory", (req, res) -> {
			OrderHistory orderHistory = new OrderHistory();
			try {
				MultiMap<String> params = new MultiMap<String>();
				UrlEncoded.decodeTo(req.body(), params, "UTF-8");
				BeanUtils.populate(orderHistory, params);
			}
			catch (Exception e) {
				halt(501);
				return null;
			}
			return retailerDao.getOrderHistory(orderHistory.getRetailerusername());
		}, new JsonTransformer());

	}

	private void addAuthenticatedUser(Request request, User u)
	{
		request.session().attribute(USER_SESSION_ID, u);

	}

	private User getAuthenticatedUser(Request request)
	{
		return request.session().attribute(USER_SESSION_ID);
	}

	public KisankrishiServices getKisanKrishiServices()
	{
		return kisanKrishiServices;
	}

	public void setKisanKrishiServices(KisankrishiServices kisanKrishiServices)
	{
		this.kisanKrishiServices = kisanKrishiServices;
	}

}
