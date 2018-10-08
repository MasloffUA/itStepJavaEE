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
	<%! String[] inputText = new String[4]; %>
	<%! List<Integer> userValue = new ArrayList<>(); %>
	<%! List<String> history = new ArrayList<>(); %>
	<%! boolean isGame = false;%>
	<%! String message =""; %>
	<%
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
			try{		
			message = request.getMethod();
			if ("POST".equalsIgnoreCase(request.getMethod())){
				inputText = request.getParameterValues("userNumber");	
				s = inputText[0];
				System.out.println(s);
				
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
						
						history.add(s);
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
						if (history.size()>3){
							isGame = false;
							message = message + "Игра окончена";
						} else {
							message = message + "Осталось " + 3 + " попыток";
						}
					}
				}
			}
			} catch (NumberFormatException e){
				message = s + " - какие-то лишние символы у Вас в числе.";
			}
		}
	%>

	 <form method="post">
	Введите число:<input type="text" name="userNumber"></input>
	<input type="submit" value="Пробуем"></form>
	<%=message %>
	
 	<br>

</body>
</html>