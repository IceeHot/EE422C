package assignment7;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.*;
import java.net.Socket;
import java.util.*;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ClientMain extends Application {
	
	/* Socket objects */
	static BufferedReader in;
	static PrintWriter out;
	static Socket sock;
	
	/* Overall UI tools */
	static Stage secondaryStage;
	static Scene loginScene;
	static Scene GUIScene;
	static GridPane scene = new GridPane();
	static GridPane GUI = new GridPane();
	static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	static ArrayList<String> selectedFriends = new ArrayList<String>();
	static ArrayList<String> friends = new ArrayList<String>();
	static ArrayList<String> friendBox = new ArrayList<String>();
	static String activeConvo = new String("");
	static String command = new String();
	static boolean socketCreated = false;
	static boolean isReady = false;
	static Timeline listener;
	
	/* Login Objects */
	static GridPane loginPane = new GridPane();
	static String userName = new String();
	static Label userLabel = new Label("Username:");
	static Label passLabel = new Label("Password:");
	static Label ipLabel = new Label("IP Address:");
	static Label invalid = new Label("Invalid Password!");
	static Label cln0 = new Label(":");
	static Label cln1 = new Label(":");
	static Label cln2 = new Label(":");
	static TextField userField = new TextField();
	static TextField passField = new TextField();
	static TextField ip0 = new TextField() {
		@Override public void replaceText(int start, int end, String text) { if (text.matches("[0-9]*")) { super.replaceText(start, end, text); } }
	    @Override public void replaceSelection(String text) { if (text.matches("[0-9]*")) { super.replaceSelection(text); } }
	};
	static TextField ip1 = new TextField() {
		@Override public void replaceText(int start, int end, String text) { if (text.matches("[0-9]*")) { super.replaceText(start, end, text); } }
	    @Override public void replaceSelection(String text) { if (text.matches("[0-9]*")) { super.replaceSelection(text); } }
	};
	static TextField ip2 = new TextField() {
		@Override public void replaceText(int start, int end, String text) { if (text.matches("[0-9]*")) { super.replaceText(start, end, text); } }
	    @Override public void replaceSelection(String text) { if (text.matches("[0-9]*")) { super.replaceSelection(text); } }
	};
	static TextField ip3 = new TextField() {
		@Override public void replaceText(int start, int end, String text) { if (text.matches("[0-9]*")) { super.replaceText(start, end, text); } }
	    @Override public void replaceSelection(String text) { if (text.matches("[0-9]*")) { super.replaceSelection(text); } }
	};
	static Button loginButton = new Button("Login");
	static Button createButton = new Button("Create");
	static VBox userVBox = new VBox();
	static VBox passVBox = new VBox();
	static VBox ipVBox = new VBox();
	static HBox loginHBox = new HBox();
	static HBox createHBox = new HBox();
	static HBox ipHBox = new HBox();
	
	/* GUI Objects */
	static MenuButton friendList = new MenuButton("Friends");
	static TextArea chatArea = new TextArea();
	static TextField chatField = new TextField();
	static TextField friendField = new TextField();
	static Button addFriend = new Button("Add Friend");
	static Button closeChat = new Button("Leave Chat");
	static Button deleteFriend = new Button("Delete Selected");
	static Button send = new Button("Send");
	static Button startChat = new Button("Start Chat");
	static Button logOut = new Button("Log Out");
	static Button accept = new Button("Accept");
	static Button deny = new Button("Deny");
	static Label requestLabel = new Label();
	static GridPane friendPane = new GridPane();
	static GridPane requestPane = new GridPane();
	static GridPane chatPane = new GridPane();
	static VBox leftVBox = new VBox();
	
	/* Window size */
	static double winWidth = screenSize.getWidth();
	static double winHeight = screenSize.getHeight();
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		secondaryStage = primaryStage;
		
		runConfig();
		
		scene.add(loginPane, 0, 0);
		
		GUI.add(leftVBox, 0, 0);
		GUI.add(chatPane, 1, 0);
		
		
		loginScene = new Scene(scene, winWidth, winHeight);
		
		/* Set stage */
        primaryStage.setScene(loginScene);
	    primaryStage.show();
	    
	}
	
	private static void runConfig() {
		
		/* Configure everything */
		sceneConfig();
		loginConfig();
		GUIConfig();
		
		/* Start listeners */
		screenListener();
		loginListener();
		createListener();
		startChatListener();
		sendListener();
		chatEnterListener();
		addFriendListener();
		friendEnterListener();
		deleteFriendListener();
		acceptListener();
		denyListener();
		closeChatListener();
		quitListener();
		
	}
	
	private static void startListener() {
		
		listener = new Timeline(new KeyFrame(Duration.millis(250), ae -> { try { listen(); } catch (IOException e) { e.printStackTrace(); }}));
		listener.setCycleCount(Animation.INDEFINITE);
		listener.play();
	}
	
	/**
	 * Configure overall scene
	 */
	private static void sceneConfig() {
		scene.setPadding(new Insets(10, 10, 10, 10));
		scene.setAlignment(Pos.CENTER);
	}
	
	/**
	 * Configure login screen
	 */
	private static void loginConfig() {
		
		/* Add spacing to loginPane */
		loginPane.setMinSize(200, 0);
		loginPane.setVgap(20);
		
		/* Login column constraints */
	    ColumnConstraints loginCol0 = new ColumnConstraints();
	    loginCol0.setPercentWidth(50);
	    ColumnConstraints loginCol1 = new ColumnConstraints();
	    loginCol1.setPercentWidth(50);
	    loginPane.getColumnConstraints().addAll(loginCol0, loginCol1);
		
		/* Login row constraints */
		RowConstraints loginRow0 = new RowConstraints();
		loginRow0.setPercentHeight(25);
		RowConstraints loginRow1 = new RowConstraints();
		loginRow1.setPercentHeight(25);
		RowConstraints loginRow2 = new RowConstraints();
		loginRow2.setPercentHeight(25);
		RowConstraints loginRow3 = new RowConstraints();
		loginRow3.setPercentHeight(25);
		loginPane.getRowConstraints().addAll(loginRow0, loginRow1, loginRow2, loginRow3);
		
		/* Limit text field lengths */
		addTextLimiter(ip0, 3);
		addTextLimiter(ip1, 3);
		addTextLimiter(ip2, 3);
		addTextLimiter(ip3, 3);
		
		/* Set up HBoxes */
		ipHBox.setSpacing(5);
		loginHBox.setAlignment(Pos.CENTER);
		createHBox.setAlignment(Pos.CENTER);
		loginButton.setMinHeight(30);
		loginButton.setMinWidth(60);
		createButton.setMinHeight(30);
		createButton.setMinWidth(60);
		ipHBox.setMaxWidth(200);
		loginHBox.getChildren().add(loginButton);
		createHBox.getChildren().add(createButton);
		ip0.setMinHeight(30);
		ip1.setMinHeight(30);
		ip2.setMinHeight(30);
		ip3.setMinHeight(30);
		cln0.setMinHeight(30);
		cln1.setMinHeight(30);
		cln2.setMinHeight(30);
		ipHBox.getChildren().addAll(ip0, cln0, ip1, cln1, ip2, cln2, ip3);
		
		/* Set up VBoxes */
		userVBox.setSpacing(5);
		passVBox.setSpacing(5);
		ipVBox.setSpacing(5);
		userLabel.setMinHeight(30);
		userField.setMinHeight(30);
		userVBox.getChildren().addAll(userLabel, userField);
		passLabel.setMinHeight(30);
		passField.setMinHeight(30);
		passVBox.getChildren().addAll(passLabel, passField);
		ipVBox.getChildren().addAll(ipLabel, ipHBox);
		
		/* Add everything to loginPane */
		loginPane.add(userVBox, 0, 0, 2, 1);
		loginPane.add(passVBox, 0, 1, 2, 1);
		loginPane.add(ipVBox, 0, 2, 2, 1);
		loginPane.add(loginHBox, 0, 3);
		loginPane.add(createHBox, 1, 3);
		
	}
	
	private static void socketConfig() {
    	try { sock = new Socket(ip0.getText() + "." + ip1.getText() + "." + ip2.getText() + "." + ip3.getText(), 4560); }
    	catch (Exception e1) { e1.printStackTrace(); }
    	try {
    		in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
    		out = new PrintWriter(sock.getOutputStream());
    	} catch (Exception e2) { e2.printStackTrace(); }
	}
	
	/**
	 * Configure GUI
	 */
	private static void GUIConfig() {
		
		/* GUI configuration */
		GUI.setPadding(new Insets(20, 20, 20, 20));
		GUI.setHgap(20);
		GUI.setAlignment(Pos.CENTER);

		/* GUI column constraints */
	    ColumnConstraints GUICol0 = new ColumnConstraints();
	    GUICol0.setPercentWidth(25);
	    ColumnConstraints GUICol1 = new ColumnConstraints();
	    GUICol1.setPercentWidth(75);
	    GUI.getColumnConstraints().addAll(GUICol0, GUICol1);
	    
	    /* GUI row constraints */
	    RowConstraints GUIRow0 = new RowConstraints();
	    GUIRow0.setPercentHeight(100);
	    GUI.getRowConstraints().addAll(GUIRow0);
	}
	
	/**
	 * Configure left side of screen
	 */
	private static void leftConfig() {
		
		/* friendPane column constraints */
	    ColumnConstraints friendCol0 = new ColumnConstraints();
	    friendCol0.setPercentWidth(50);
	    friendCol0.setHalignment(HPos.CENTER);
	    ColumnConstraints friendCol1 = new ColumnConstraints();
	    friendCol1.setPercentWidth(50);
	    friendCol1.setHalignment(HPos.CENTER);
	    friendPane.getColumnConstraints().addAll(friendCol0, friendCol1);
		
		/* friendPane row constraints */
		RowConstraints friendRow0 = new RowConstraints();
		friendRow0.setPercentHeight(50);
		RowConstraints friendRow1 = new RowConstraints();
		friendRow1.setPercentHeight(50);
		RowConstraints friendRow2 = new RowConstraints();
		friendRow2.setPercentHeight(50);
		friendPane.getRowConstraints().addAll(friendRow0, friendRow1, friendRow2);
		
		/* Configure friendPane */
		friendPane.setHgap(10);
		friendPane.setVgap(20);
		friendPane.add(friendList, 0, 0, 2, 1);
		startChat.setMinHeight(30);
		startChat.setMinWidth(50);
		friendPane.add(startChat, 0, 1);
		deleteFriend.setMinHeight(30);
		deleteFriend.setMinWidth(50);
		friendPane.add(deleteFriend, 1, 1);
		friendField.setMaxWidth(200);
		friendField.setMinHeight(30);
		friendPane.add(friendField, 0, 2);
		addFriend.setMinHeight(30);
		addFriend.setMinWidth(50);
		friendPane.add(addFriend, 1, 2);
		
		/* Configure acceptPane */
		requestPane.setHgap(10);
		requestPane.setVgap(10);
		requestPane.setAlignment(Pos.CENTER);
		requestLabel.setMinHeight(30);
		requestPane.add(requestLabel, 0, 0, 2, 1);
		accept.setMinHeight(30);
		accept.setMinWidth(50);
		requestPane.add(accept, 0, 1);
		deny.setMinHeight(30);
		deny.setMinWidth(50);
		requestPane.add(deny, 1, 1);
		
		/* Configure left VBox */
		leftVBox.setSpacing(winHeight / 10);
		leftVBox.setAlignment(Pos.CENTER);
		closeChat.setMinHeight(30);
		closeChat.setMinWidth(50);
		logOut.setMinHeight(30);
		logOut.setMinWidth(50);
		leftVBox.getChildren().addAll(friendPane, requestPane, closeChat, logOut);
		
	}
	
	/**
	 * Configure right side of screen
	 */
	private static void chatConfig() {
		
		/* chatPane column constraints */
	    ColumnConstraints chatCol0 = new ColumnConstraints();
	    chatCol0.setPercentWidth(80);
	    chatCol0.setHalignment(HPos.CENTER);
	    ColumnConstraints chatCol1 = new ColumnConstraints();
	    chatCol1.setPercentWidth(20);
	    chatCol1.setHalignment(HPos.CENTER);
	    chatPane.getColumnConstraints().addAll(chatCol0, chatCol1);
		
		/* friendPane row constraints */
		RowConstraints chatRow0 = new RowConstraints();
		chatRow0.setPercentHeight(90);
		chatRow0.setValignment(VPos.CENTER);
		RowConstraints chatRow1 = new RowConstraints();
		chatRow1.setPercentHeight(10);
		chatRow1.setValignment(VPos.CENTER);
		chatPane.getRowConstraints().addAll(chatRow0, chatRow1);
		
		chatPane.setHgap(10);
		chatPane.setVgap(20);
		chatPane.add(chatArea, 0, 0, 2, 1);
		chatField.setMinHeight(50);
		chatPane.add(chatField, 0, 1);
		send.setMinWidth(100);
		send.setMinHeight(50);
		chatPane.add(send, 1, 1);
		
	}
	
	/**
	 * Populate friend list
	 * @throws IOException 
	 */
	private static void friendConfig() throws IOException {
		
		friendList.setMinHeight(30);
		friendList.setMinWidth(100);
		friendList.setMaxWidth(500);
		
		out.println("FriendList");
		out.flush();
		
		String newFriend = in.readLine();
		System.out.println(newFriend);
		while (!newFriend.equals("Overf")) {
			System.out.println(newFriend);
			if (friendBox.contains(newFriend) || newFriend.equals("")) { newFriend = in.readLine(); continue; }
			CheckBox cb = new CheckBox(newFriend);
    		checkBoxListener(cb);
    		CustomMenuItem item = new CustomMenuItem(cb);
    		item.setHideOnClick(false);
    		friendList.getItems().add(item);
    		friendBox.add(newFriend);
    		newFriend = in.readLine();
		}
		/*
		for (String s : friends) {
			if (friendBox.contains(s)) { continue; }
			CheckBox cb = new CheckBox(s);
    		checkBoxListener(cb);
    		CustomMenuItem item = new CustomMenuItem(cb);
    		item.setHideOnClick(false);
    		friendList.getItems().add(item);
    		friendBox.add(s);
		}
		*/
	}
	
	/**
	 * Limit text field length
	 * 
	 * @param tf is the text field to limit
	 * @param maxLength is the length to limit to
	 */
	private static void addTextLimiter(final TextField tf, final int maxLength) {
	    tf.textProperty().addListener(new ChangeListener<String>() {
	        @Override
	        public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
	            if (tf.getText().length() > maxLength) {
	                String s = tf.getText().substring(0, maxLength);
	                tf.setText(s);
	            }
	            if (Integer.parseInt(tf.getText()) > 255) {
	                String s = tf.getText().substring(0, maxLength - 1);
	                tf.setText(s);
	            }
	        }
	    });
	}
	
	/**
	 * Listen to checkbox changes
	 * 
	 * @param cb is checkbox to listen to
	 */
	private static void checkBoxListener(final CheckBox cb) {
		cb.selectedProperty().addListener(new ChangeListener<Boolean>() {
			public void changed(ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) {
				if (cb.isSelected()) { selectedFriends.add(cb.getText()); }
				if (!cb.isSelected()) { selectedFriends.remove(cb.getText()); }
	        }
		});
	}
	
	/**
	 * Listen to login button
	 */
	private static void loginListener() {
		loginButton.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	
		    	if (!socketCreated) { socketConfig(); socketCreated = true; }
		    	
		    	out.println("Login");
		    	out.flush();
		    	out.println(userField.getText());
		    	out.flush();
		    	out.println(passField.getText());
		    	out.flush();
		    	
		    	String newString = null;
		    	try { while (newString == null) { newString = in.readLine(); } }
		    	catch (Exception e1) { e1.printStackTrace(); }
		    	
		    	if (newString.equals("Success")) {
		    		try { friendConfig(); }
		    		catch (IOException e1) { e1.printStackTrace(); }
			    	leftConfig();
			    	chatConfig();
			    	userName = userField.getText();
					GUIScene = new Scene(GUI, winWidth, winHeight);
			        secondaryStage.setScene(GUIScene);
			        startListener();
		    	} else {
		    		// TODO: Acutal invalid password handling
		    		System.out.println("Invalid Username or Password!");
		    	}
	        }
	    });
	}
	
	/**
	 * Listen to create button
	 */
	private static void createListener() {
		createButton.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {

		    	if (!socketCreated) { socketConfig(); socketCreated = true; }
		    	
		    	out.println("Create User");
		    	out.flush();
		    	out.println(userField.getText());
		    	out.flush();
		    	out.println(passField.getText());
		    	out.flush();
		    	
		    	String newString = null;
		    	try { while (newString == null) { newString = in.readLine(); } }
		    	catch (Exception e1) { e1.printStackTrace(); }
		    	
		    	if (newString.equals("Success")) {
			    	try {
						friendConfig();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			    	leftConfig();
			    	chatConfig();
			    	userName = userField.getText();
					GUIScene = new Scene(GUI, winWidth, winHeight);
					isReady = true;
			        secondaryStage.setScene(GUIScene);
			        startListener();
		    	} else {
		    		// TODO: Acutal invalid password handling
		    		System.out.println("User already exists!");
		    	}
	        }
	    });
	}
	
	/**
	 * Open up a new chat
	 */
	private static void startChatListener() {
		startChat.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	listener.stop();
		    	activeConvo = "";
		    	selectedFriends.add(userName);
		    	Collections.sort(selectedFriends);
		    	out.println("New Chat");
		    	out.flush();
		    	for (int i = 0; i < selectedFriends.size(); i++) {
		    		activeConvo += selectedFriends.get(i);
		    		System.out.println(selectedFriends.get(i));
		    		out.println(selectedFriends.get(i));
		    		out.flush();
		    	}
		    	System.out.println(activeConvo);
		    	out.println("no more");
		    	out.flush();
		    	selectedFriends.remove(userName);
		    	chatArea.clear();
		    	listener.play();
	        }
	    });
	}
	
	private static void sendListener() {
		send.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	listener.stop();
		    	out.println("Addchit");
		    	out.flush();
		    	out.println(activeConvo);
		    	out.flush();
		    	out.println(userName + ": " + chatField.getText());
		    	out.flush();
		    	chatField.clear();
		    	listener.play();
	        }
	    });
	}
	
	/**
	 * Listen for enter key pressed on chat field
	 */
	private static void chatEnterListener() {
		chatField.setOnKeyPressed(new EventHandler<KeyEvent>() {
	        @Override public void handle(KeyEvent ke) {
	            if (ke.getCode().equals(KeyCode.ENTER)) {
	            	if (!chatField.getText().equals("")) { send.fire(); }
	            }
	        }
	    });
	}
	
	/**
	 * Listen for enter key pressed on add friend field
	 */
	private static void friendEnterListener() {
		friendField.setOnKeyPressed(new EventHandler<KeyEvent>() {
	        @Override public void handle(KeyEvent ke) {
	            if (ke.getCode().equals(KeyCode.ENTER)) {
	            	if (!friendField.getText().equals("")) { addFriend.fire(); }
	            }
	        }
	    });
	}
	
	/**
	 * Add a new friend
	 */
	private static void addFriendListener() {
		addFriend.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	listener.stop();
		    	out.println("AddFriend");
		    	out.flush();
		    	out.println(friendField.getText());
		    	out.flush();
		    	friends.add(friendField.getText());
		    	friendField.clear();
		    	String newFriend = null;
		    	try {
					while (!in.ready()) { }
					newFriend = in.readLine();
					if (newFriend.equals("newfriend")) { friendConfig(); }
				} catch (IOException e1) { e1.printStackTrace(); }
		    	listener.play();
	        }
	    });
	}
	
	private static void deleteFriendListener() {
		deleteFriend.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	listener.stop();
		    	out.println("DeleteFriend");
		    	out.flush();
		    	for (int i = 0; i < selectedFriends.size(); i++) {
		    		friends.remove(selectedFriends.get(i));
		    		out.println(selectedFriends.get(i));
		    		out.flush();
		    	}
		    	out.println("Over");
		    	out.flush();
		    	try { friendConfig(); }
		    	catch (IOException e1) { e1.printStackTrace(); }
		    	listener.play();
	        }
	    });
	}
	
	private static void acceptListener() {
		accept.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	// TODO: make them feel good
	        }
	    });
	}
	
	private static void denyListener() {
		deny.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	listener.stop();
		    	out.println("DeleteFriend");
		    	out.flush();
		    	out.println(requestLabel.getText().split(" ")[0]);
		    	out.flush();
		    	out.println("Over");
		    	out.flush();
		    	friends.remove(requestLabel.getText().split(" ")[0]);
		    	try { friendConfig(); }
		    	catch (IOException e1) { e1.printStackTrace(); }
		    	listener.play();
	        }
	    });
	}
	
	private static void closeChatListener() {
		closeChat.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	listener.stop();
		    	out.println("End Chat");
		    	out.flush();
		    	out.println(activeConvo);
		    	out.flush();
		    	out.println(userName);
		    	out.flush();
		    	chatArea.clear();
		    	listener.play();
	        }
	    });
	}
	
	/**
	 * Log out
	 */
	private static void quitListener() {
		logOut.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	out.println("Quit");
		    	out.flush();
		    	listener.stop();
		    	socketCreated = false;
		    	secondaryStage.setScene(loginScene);
	        }
	    });
	}
	
	/**
	 * Listen for changes in screen size
	 */
	private static void screenListener() {
		scene.widthProperty().addListener(new ChangeListener<Number>() {
		    @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {
		        winWidth = newSceneWidth.doubleValue();
		    }
		});
		scene.heightProperty().addListener(new ChangeListener<Number>() {
		    @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneHeight, Number newSceneHeight) {
		        winHeight = newSceneHeight.doubleValue();
		    }
		});
	}
	
	private static void listen() throws IOException {
		command = "";
		if (in.ready()) {
			command = in.readLine();
			System.out.println(command);
	    	if (command.equals("friendadd")) {
	    		command = in.readLine();
	    		if (!friends.contains(command)) {
			    	out.println("AddFriend");
			    	out.flush();
			    	out.println(command);
			    	out.flush();
			    	friends.add(command);
		    		requestLabel.setText(command + " has added you!");
					command = in.readLine();
					friendConfig();
	    		}
	    	}
	    	else if (command.equals("UpdateChat")) {
	    		System.out.println("Updating chat!");
	    		command = in.readLine();
	    		System.out.println(command);
	    		if (command.equals(activeConvo)) {
	    			chatArea.clear();
	    			System.out.println("Just cleared");
    				command = in.readLine();
	    			while (!command.equals("qwertyuiop")) {
	    				System.out.println(command);
	    	    		chatArea.appendText(command);
	    				command = in.readLine();
	    			}
    	    		System.out.println("I've reached the end");
	    		}
	    		else {
		    		System.out.println("Active conversation didn't match");
		    		while (!command.equals("qwertyuiop")) {
		    			command = in.readLine();
					}
	    		}
	    	}
	    	else if (command.equals("Addchit")) {
	    		System.out.println("entered");
	    		command = in.readLine();
	    		if (command.equals(activeConvo)) {
	    			System.out.println("entered again");
	    			command = in.readLine();
		    		chatArea.appendText(command);
		    		chatArea.appendText("\n");
	    		}
	    	}
		}
	}
}
