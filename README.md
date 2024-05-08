HealthGateWay 2.0 QA Automation

Automation framework installation steps:
Download latest 8th version of JDK (E.g. Java SE 8u341):
http://www.oracle.com/technetwork/java/javase/downloads/index.html
1. Add Java to your PATH environment variable. E.g. C:\Program Files\Java\jdk1.8.0_341\bin
2. Add   %JAVA_HOME%\bin

Download and install your favourite Java IDE. E.g. IntelliJ IDEA Community edition:
https://www.jetbrains.com/idea/download/#section=windows

Up and Running with git and GitHub:
1.	Download git https://git-scm.com/download/win
2.	Keep default configuration on installation process unless you already know what you want to change
3.	Once complete run Git Bash console as an Administrator
4.	In git console generate new ssh key https://help.github.com/articles/generating-a-new-ssh-key-and-adding-it-to-the-ssh-agent/ 
	a. ssh-keygen -t rsa -b 4096 -C "your_email@example.com" 
	b. If you want to have password prompt before commit set up password, otherwise leave empty 
	c. start ssh agent: eval $(ssh-agent -s)
	d. add ssh key to an agent: ssh-add ~/.ssh/id_rsa
	e. copy your public key to the clipboard:  clip < ~/.ssh/id_rsa.pub	
	f. 	go to https://github.com/NameOfYourAccount
	Sign in with your credentials, go to “Your profile” -> “Settings” -> “SSH and GPG keys” ->
	“New SSH key” paste you key into the “Key box” and click “Add SSH key”

5.	Open GitBash as an admin, select folder for your automation project and clone it: 	
git clone ssh://git@github.com:IgorEmelyanovPHSA/immsauto.git      
OR:
git clone git@github.com:IgorEmelyanovPHSA/immsauto.git
