package com.epam.ekids.base.utils;

import com.epam.ekids.base.resource.PropertyLoader;

public class RestrictionManager {
	
	private static boolean changebleImgAllow;
	private static boolean changebleImgAllowEnemy;
	private static boolean changebleImgAllowAmmo;
	private static boolean isOn;
	
	public static boolean isChangeImgAllow() {
		return !isOn || changebleImgAllow;
	}
	
	public static boolean isChangeImgAllowEnemy() {
		return !isOn || changebleImgAllowEnemy;
	}
	
	public static boolean isChangeImgAllowAmmo() {
		return !isOn || changebleImgAllowAmmo;
	}
	
	public static void switchOn() {
		isOn = true;
	}	
	
	public static void checkCode(String code) {
		
		if(code != null) {
			if(code.startsWith("cim") & checkPersonalCode(code.substring(3))){
				changebleImgAllow = true;
			} else if(code.startsWith("enm") & checkPersonalCode(code.substring(3))){
				changebleImgAllowEnemy = true;
			} else if(code.startsWith("amm") & checkPersonalCode(code.substring(3))){
				changebleImgAllowAmmo = true;
			}
			
			
		}
	}
	
	private static boolean checkPersonalCode(String code) {
		Integer inCode = Integer.valueOf(code);
		
		String pcode = PropertyLoader.getMessage("personalcode");
		Integer dpcode = Integer.valueOf(pcode);
		
		return inCode % dpcode == 0;
	}



	
	

}
