package com.bursuc.location.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bursuc.location.entities.Location;
import com.bursuc.location.service.LocationService;
import com.bursuc.location.util.EmailUtil;

@Controller
public class LocationController {
	
	@Autowired
	LocationService service;
	
	@Autowired
	EmailUtil emailUtil;
	
	@RequestMapping("/showCreate")
	public String showCreate() {
		return "createLocation";
	}
	
	@RequestMapping("/saveLoc")
	public String saveLocation(@ModelAttribute("location") Location location, Model model) {
		Location locationSaved = service.saveLocation(location);
		String msg = "Locaton saved with id: " + locationSaved.getId();
		model.addAttribute("msg", msg);
		emailUtil.sendEmail("albustube@gmail.com", "Location saved", "Location " + location.getName() + " saved with success.");
		return "createLocation";
	}
	
	@RequestMapping("/displayLocations")
	public String displayLocations(Model model) {
		List<Location> locations = service.getAllLocations();
		model.addAttribute("locations", locations);
		return "displayLocations";
	}
	
	@RequestMapping("/deleteLocation")
	public String deleteLocation(@RequestParam int id, Model model) {
		Location location = service.getLocationById(id);
		service.deleteLocation(location);
		List<Location> locations = service.getAllLocations();
		model.addAttribute("locations", locations);
		return "displayLocations";
	}
	
	@RequestMapping("/showEdit")
	public String showEdit(@RequestParam int id, Model model) {
		Location location = service.getLocationById(id);
		model.addAttribute("location", location);
		return "editLocation";
	}
	
	@PostMapping("/editLocation")
	public String editLocation(@ModelAttribute("location") Location location, Model model) {
		service.updateLocation(location);
		model.addAttribute("locations", service.getAllLocations());
		return "displayLocations";
	}
}
