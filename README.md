# Агент АТС Asterisk

Проект написан с применением GUI Swing

Собирать проект с помощью /other/apache-maven-3.6.0
Из командной строки в корне проекта:
	mvn clean compile assembly:single
	
В папке /other/ собранный проект со всеми зависимостями asteragent-0.0.1-jar-with-dependencies.jar
Необходима установленная JRE 1.8+
Запуск из командной строки:
	java -jar asteragent-0.0.1-jar-with-dependencies.jar
	
После запуска агент попросит данные AMI пользователя, внутренний номер и адрес сервера