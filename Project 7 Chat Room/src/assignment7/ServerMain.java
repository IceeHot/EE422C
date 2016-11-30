package assignment7;
import java.io.*;
import java.net.*;
import java.util.*;


public class ServerMain {
	//static int PortNo = 5555;
	static Object o= new Object();
	static HashMap<String,String> usernamepassword= new HashMap<String,String>();
	static HashMap<String,ObservablePrint> connectedusr ;
	static HashMap <String, ChatRoomObject> convolist = new HashMap<String,ChatRoomObject>();
	static  ArrayList<Socket> UserSocks = new ArrayList<Socket>();
	static ArrayList<String> Sockusernames = new ArrayList<String>();
	static HashMap<String,BufferedReader> bemyfriend ;
	static ArrayList<Integer> online = new ArrayList<Integer>();
    static  int chatnumber =0;
    static File UserData;
    static File Data;
   
    	
  
    	
    
	
	public static void main(String[] args) throws Exception{
		
		try{
			boolean controller=true;
			
			ServerSocket Server  = new ServerSocket(4580);
			 CreateFileStructure();
			 connectedusr = new HashMap<String, ObservablePrint>();
			 bemyfriend  =new HashMap<String,BufferedReader> ();
			
			while (controller){
				
				Socket newsock = Server.accept();
				System.out.println("Connection secured");
				
				
				ClientHandler newclient =new ClientHandler(newsock);
				
				Thread t = new Thread(newclient);
				t.start();
				
			
			}
			Server.close();
			
		   }catch(Exception e){e.printStackTrace();}
	}
	
	
	
	public static void CreateFileStructure() throws Exception{
		
		 File registry = new File(ServerMain.class.getProtectionDomain().getCodeSource().getLocation().toURI());
		   Data = new File(registry.getAbsolutePath()+java.io.File.separator+"information");
		    boolean exists = Data.exists();
		    if(!exists){
		    	Data.mkdir();
		    }
		   UserData = new File(Data.getAbsolutePath()+java.io.File.separator+"userdata.txt");
		   boolean existsa = UserData.exists();
		   if(!existsa){
			   UserData.createNewFile();
		 }
		   
		   Scanner scanfordata = new Scanner(UserData);
		   while(scanfordata.hasNext()){
			   String uname = scanfordata.next();
			   String pword = scanfordata.next();
			   usernamepassword.put(uname,pword);
			   UserSocks.add(null);
			   Sockusernames.add(uname);
			   online.add(0);
			  
			   
		   }
		  scanfordata.close();
		
	}
	
		public static class ClientHandler implements Runnable{
			private BufferedReader reader ;
			private ObservablePrint writer;
			private  Socket thissocket;
			private String username;
			private String password;
			private ArrayList <String> friendlist = new ArrayList<String>();
			private boolean loggedin =false;
			public ClientHandler(Socket clientsock){
				this.thissocket= clientsock;
				try{
                       reader = new BufferedReader(new InputStreamReader(thissocket.getInputStream()));
                       writer = new ObservablePrint ((thissocket.getOutputStream()));
                      // writer.println("Get on that GUI bitch");
                     //  writer.flush();
                       
					
			}catch(Exception f){f.printStackTrace();}
			}
			
			public void run(){ 
				boolean incomplete = true ;
				while (incomplete){
				
				String action=null;
				try {
					action = reader.readLine();
				
				while(action==null){action=reader.readLine();}
				
				
			
				
			//System.out.println(action);
			String chkusername = reader.readLine();
			//System.out.println(chkusername);
			String chkpassword = reader.readLine();
			//System.out.println(chkpassword);
				
		//synchronized(o){
            if(action.equals("Create User")){
           
                if( usernamepassword.containsKey(chkusername)){
                	writer.println("exists");
                	writer.flush();
                
                }
             
                else {
                	
                	writer.println("Success");
                	writer.flush();
                	
                	password = chkpassword;
                	username = chkusername;
                	UserSocks.add(thissocket);
                	Sockusernames.add(chkusername);
                	usernamepassword.put(chkusername,chkpassword);
                	PrintWriter uptracker;
                	uptracker = new PrintWriter(new FileWriter(UserData,true));
					
    				
      				 uptracker.append(chkusername);
      				 uptracker.append("\n");
      				 uptracker.append(chkpassword);
      				 uptracker.append("\n");
      				 uptracker.close();
                   	
                	
                	
                	online.add(1);
                	incomplete =false;
                	loggedin =true;
                	connectedusr.put(chkusername,writer);
                	bemyfriend.put(chkusername,reader );
                	
               }
            }
				
            else if (action.equals("Login") && usernamepassword.containsKey(chkusername) ){
            	
            	String value = (String) usernamepassword.get(chkusername);
            	if (value.equals(chkpassword)){
            		writer.println("Success");
            		writer.flush();
            		password =chkpassword;
            		username =chkusername;
            		for(int i = 0 ; i< UserSocks.size();i++){
            			if (Sockusernames.get(i).equals(username)){
            				UserSocks.set(i,thissocket);
            				online.add(1);
            				incomplete =false;
            				loggedin=true;
            				connectedusr.put(username,writer);
            				bemyfriend.put(username, reader);
            			}
            			
            		}
            		
            	}
            	
            	else{
            		writer.println("Invalid password");
            		writer.flush();
            	}
             }
            
            
            else{
            	writer.println("Invalid password");
        		writer.flush();
            	
            }
				}catch(Exception j){
					j.printStackTrace();
				}
      //  }
            
			
				
	}
			
				
				while (loggedin){
					System.out.println("Here");
					String command = "";
					try{
					
					while ((command=  reader.readLine() )==null){
						Thread.sleep(200);
		             }
					System.out.println("should switch");
					switch (command){
					case "Quit":   File popfriends = new File(Data.getAbsolutePath()+java.io.File.separator+username+".txt");
							                        PrintStream pr = new PrintStream(popfriends);
							                        if(!friendlist.isEmpty()){
													for (String jh: friendlist){
														pr.println(jh);
														System.out.println(jh);
													}}
						                         pr.close();
						                 		connectedusr.remove(username);
						                 		bemyfriend.remove(username);
						                 		loggedin = false;
					                            
						                 		break;
					
					case "New Chat":             
						
						
						
						
						
						
						ArrayList<String> peoplechatting = new ArrayList<String> ();
											  System.out.println("Making new new chat");
					                           String p;
											   while (!(p=reader.readLine()).equals("no more")){
												  peoplechatting.add(p); 
												  System.out.println(p);
												   }
											   ArrayList<String> checkifexists = new ArrayList<String>();
											   for (String count : peoplechatting){
												   checkifexists.add(count);
											   }
											   Collections.sort(checkifexists);
											   String g ="";
											   for (String q: checkifexists){
												   g+=q;
											   }
											   
											   
											   	File checker;
											   
								               checker = new File(Data.getAbsolutePath()+java.io.File.separator+g+".txt");
								               boolean qq =checker.exists();
								               System.out.println(qq +"check ");
								               
								               if(qq){
								            	  writer.println("UpdateChat\n"+g);
								            	 // writer.println(g);
								            	 Scanner updation = new Scanner(checker);
													while (updation.hasNextLine()){
													
													 writer.println(updation.nextLine());
													
													}
													 updation.close();
													 writer.println("qwertyuiop");
								               }
                                         
									            
											   
											   if(!convolist.isEmpty() ){
												   System.out.println("Check one");
											   if(convolist.containsKey(g)){
												   System.out.println("Printing past");
												   
												 //  writer.println("preparepast");
												   //writer.flush();
												  convolist.get(g).Printpast(g);
											   }
											   
											  
													
											   
											   
											   else{System.out.println("Creating chatroom");
												   
											   new ChatRoomObject(peoplechatting);}
											   }
											   if (convolist.isEmpty()){ 
												   
											   new ChatRoomObject(peoplechatting);}
											  
											 //  convolist.put(,peoplechatting);
						                       break;
					
					case "End Chat":            String b = reader.readLine();
					 							convolist.get(b).deleteObserver(connectedusr.get(username));
					 							
					 							
					
						
						                       break; 
					
					case "FriendList":       if(!friendlist.isEmpty()){  for (String jk: friendlist ){
												writer.println(jk);
												writer.flush();
								
												}}
																					
													 File readfriend = new File(Data.getAbsolutePath()+java.io.File.separator+username+".txt");
								                     Scanner sc= new Scanner(readfriend);
								                     while (sc.hasNextLine()){
								                    	 String j =sc.nextLine();
								                    	 System.out.println(j);
								                    	 if(!friendlist.contains(j)){
								                    		 	friendlist.add(j);}
								                    	 writer.println(j);
								                    	 writer.flush();
								                     }
								                     sc.close();
					
												
					                            writer.println("Overf");
					                            writer.flush();
				                                
						
						
						
						
											break;
					
					case "AddFriend":  //friendlist.add(reader.readLine());
										//System.out.println("1");
										String b1 = reader.readLine();
										//if(!friendlist.contains(b1)){
										//System.out.println("2");
										if (connectedusr.containsKey(b1)){
											connectedusr.get(b1).println("friendadd");
											connectedusr.get(b1).flush();
											connectedusr.get(b1).println(username);
											connectedusr.get(b1).flush();
										//connectedusr.get(b).println("username wants to be friends");
										//String e =null;
										//while ((e = reader.readLine())==null){Thread.sleep(300);}
										//if(e.equals("accept")){
											//writer.println("newfriend"+b);
											//writer.flush();
										//}
										//else{
											//writer.println("nonewfriends");
											//writer.flush();
											//System.out.println("3");
											//bemyfriend.get
											friendlist.add(b1);
											writer.println("newfriend");
											writer.flush();
											//System.out.println("4");
										}
										//}
										else{
								
											writer.println("No Such online User");
											writer.flush();
										}
										
										//}
						               break;
						               
					case "DeleteFriend": //friendlist.remove(reader.readLine()); 
										String oldfriend =null;
										while(((oldfriend=reader.readLine())==null )){}
										while (!(oldfriend.equals("Over"))){
										 for (int l =0; l<friendlist.size();l++){
											 if (friendlist.get(l).equals(oldfriend)){
												 friendlist.remove(l);
												 
											 }
										 }
										 oldfriend=reader.readLine();
										}
										 
										 
						                 break;
					case "Addchit":    System.out.println("entering addchit");
						               String identifier = reader.readLine();
						               System.out.println("q not null");
					                   String message = reader.readLine();
					                   System.out.println("r not null");
					                   convolist.get(identifier).UpdateChat(message);
					                   System.out.println("s not null");
						
					                   	break;
						
						
					}}catch(Exception k){k.printStackTrace();}
					
					
							
				}
				
					
		
			}	
			
			
		
}
		
		static class ObservablePrint extends PrintWriter implements Observer{
			public ObservablePrint (OutputStream a){
				super(a);
			}
			@Override
			public void update(Observable ooo , Object arg){
				System.out.println("Printing to observers");
				System.out.println(arg);
				this.println(arg);
				this.flush();
				
			}
			
			
			
		} 
		
		
		static class ChatRoomObject extends Observable{
			private int ChatNo;
			private String minutesconversation = new String("");
			private ArrayList<String> pplchattingalphabetically;
			private String convoname =new String("");
		
			
			
			ChatRoomObject(ArrayList<String> pplchatting) throws FileNotFoundException{
				synchronized(o){
					ChatNo=chatnumber;
					chatnumber+=1;
					pplchattingalphabetically = new ArrayList<String>();
					for (String s: pplchatting){
						pplchattingalphabetically.add(s);
					}
					
					Collections.sort(pplchattingalphabetically);
					for (String g: pplchattingalphabetically){
						convoname+=g;
						
					}
					System.out.println("Convolist not empty");
				
			convolist.put(convoname, this);
					
				}
				for(String p : pplchatting){
					 System.out.println("obs not null");
					this.addObserver(connectedusr.get(p));
				}
				
				
			}
			
			public String getMinutesConversation(){
				return this.minutesconversation;
			}
			
			public int getChatNo(){
				return this.ChatNo;
			}
			
			public String getConvoName(){
				return this.convoname;
			}
			
			public ArrayList<String> alphabetical(){
				return this.pplchattingalphabetically;
			}
			
			public void Printpast(String op) throws IOException{
			File tracker = new File(Data.getAbsolutePath()+java.io.File.separator+op+".txt");
			if(!tracker.exists()){
				tracker.createNewFile();
			}
			Scanner read = new Scanner(tracker);
			setChanged();
			notifyObservers("UpdateChat\n"+convoname);
			System.out.println("Printing old cha");
			//setChanged();
			//System.out.println(convoname);
			//notifyObservers (convoname+"\n");
			while (read.hasNextLine()){
				UpdateC(read.nextLine());
			}   setChanged();
			    notifyObservers("qwertyuiop");
				read.close();
				
			}
			
			
			public void UpdateChat(String update) throws IOException{
				minutesconversation+=update;
				
				setChanged();
			  System.out.println("Notifying observers");
				notifyObservers("Addchit\n"+convoname+"\n"+update+"\n");
				System.out.println("UC"+convoname);
				System.out.println(update);
				File nonsense;
				PrintWriter convotracker;
               nonsense = new File(Data.getAbsolutePath()+java.io.File.separator+convoname+".txt");
               if(!nonsense.exists()){
            	   nonsense.createNewFile();
               }
	            try {
					convotracker = new PrintWriter(new FileWriter(nonsense,true));
					
				
				 convotracker.append(update);
				 convotracker.append("\n");
				 convotracker.close();
	            } catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
			
			
			
			public void UpdateC(String update) throws IOException{
				minutesconversation+=update;
				 System.out.println("updatec not null");
				 System.out.println(convoname);
				setChanged();
				
				
			
				notifyObservers(update+"\n");
				
	           
				
				
			}
			
		}
}



