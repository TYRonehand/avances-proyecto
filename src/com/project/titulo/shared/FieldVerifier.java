package com.project.titulo.shared;


public class FieldVerifier {

	public static boolean isValidName(String name) {
		if (name == null) {
			return false;
		}
		return name.length() > 3;
	}
	
	public static boolean isValidMail(String mail){
		int arrovas=0;
		int spaces=0;
		int dots=0;
		boolean error = false;
		
		//conteo caracteres
		for(char c : mail.toCharArray())
		{
			if(c=='@')
				arrovas++;
			if(c==' ')
				spaces++;
			if(c=='.')
				dots++;
			
		}
		//verificacion dominio no mas de un punto junto
		if(mail.length()>0)
		{
			char[] dom= mail.toCharArray();
			for(int i=0 ; i<mail.length()-1 ; i++)
			{
				//si existen '..' existe error
				if(dom[i]==dom[i+1] && dom[i]=='.')
					error=true;
			}
		}else
			error=true;
		
		//condicional
		if(arrovas==1 && dots>0 && spaces==0 && error==false)
			return true;
		return false;
	}
}
