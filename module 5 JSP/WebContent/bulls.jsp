<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Быки и Коровы</title>
</head>
<body>
	<%@ page import="java.util.*"%>
	<%! List<Integer> value = new ArrayList<>(); %>
	<%! int count = 4; %>
	<%! String[] inputText = new String[5]; %>
	<%! List<Integer> userValue = new ArrayList<>(); %>
	<%! boolean isGame = false;%>
	<%! String message =""; %>
	<%
		message ="1";
		while (!isGame && value.size()<4){
			Random rnd = new Random();
			int a = rnd.nextInt(10);
			if (!value.contains(a)){
				value.add(a);
			}
		
		}
		isGame = true;

		int bulls = 0;
		int cows = 0;
		if (isGame){
			String s = null;
			message = request.getMethod();
			if ("POST".equalsIgnoreCase(request.getMethod())){
				inputText = request.getParameterValues("user");	
				s = inputText[0];
				
				if (s!=null && s.length()==4){
					int a;
					for (int i =0; i<3; i++){
						a = Integer.parseInt(s.substring(i, i+1));
						if (!userValue.contains(a)){
							userValue.add(a);
						}
					}
					a = Integer.parseInt(s.substring(3));
					if (!userValue.contains(a)){
						userValue.add(a);
					}
					
					if (userValue.size()<4){
						message = "ошиПко";
					}
					else {
						
						inputText[count] = s;
						bulls = 0;
						cows = 0;
						for (int i =0; i < 4; i++){
							int x = value.get(i);
							int y = userValue.get(i);
							if (x==y){
								bulls++;
							}
							else if (value.contains(y)){
								cows++;
							}
						}
						message = "Введённое число: " + s + ". Быков: " + bulls + ". Коров: " + cows +".";
						count --;
						if (count==0){
							isGame = false;
							message = message + "Игра окончена";
						} else {
							message = message + "Осталось " + count + " попыток";
						}
					}
				}
			
			
			
			
			
			}




		}
	%>

	 <form method="post">
	Введите число:<input type="text" name="user"></input>
	<input type="submit" value="Пробуем"></form>
	<%=message %>
	
 	<br>

</body>
</html>