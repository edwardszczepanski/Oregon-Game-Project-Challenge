import java.util.ArrayList;
import java.util.HashMap;

import org.newdawn.slick.Input;

public class Controls
{
	private HashMap<Integer, String> keyMap;
	private HashMap<Integer, String> sKeyMap;
	private ArrayList<String> controlKeys;
	private String[] keyString;
	private int[] currentControls;
	
	public Controls()
	{
		keyMap = new HashMap<Integer, String>();
		sKeyMap = new HashMap<Integer, String>();
		controlKeys = new ArrayList<String>();
		keyString = new String[3];
		currentControls = new int[3];
		
		sKeyMap.put(Input.KEY_0, "0");
		sKeyMap.put(Input.KEY_1, "1");
		sKeyMap.put(Input.KEY_2, "2");
		sKeyMap.put(Input.KEY_3, "3");
		sKeyMap.put(Input.KEY_4, "4");
		sKeyMap.put(Input.KEY_5, "5");
		sKeyMap.put(Input.KEY_6, "6");
		sKeyMap.put(Input.KEY_7, "7");
		sKeyMap.put(Input.KEY_8, "8");
		sKeyMap.put(Input.KEY_9, "9");
		sKeyMap.put(Input.KEY_A, "A");
		sKeyMap.put(Input.KEY_ADD, "+");
		sKeyMap.put(Input.KEY_APOSTROPHE, "'");
		sKeyMap.put(Input.KEY_B, "B");
		sKeyMap.put(Input.KEY_BACK, "BACKSPACE");
		sKeyMap.put(Input.KEY_BACKSLASH, "BACKSLASH");
		sKeyMap.put(Input.KEY_C, "C");
		sKeyMap.put(Input.KEY_COLON, "COLON");
		sKeyMap.put(Input.KEY_COMMA, "COMMA");
		sKeyMap.put(Input.KEY_D, "D");
		sKeyMap.put(Input.KEY_DECIMAL, "DECIMAL POINT");
		sKeyMap.put(Input.KEY_DELETE, "DELETE");
		sKeyMap.put(Input.KEY_DIVIDE, "DIVIDE");
		sKeyMap.put(Input.KEY_DOWN, "DOWN ARROW");
		sKeyMap.put(Input.KEY_E, "E");
		sKeyMap.put(Input.KEY_END, "END");
		sKeyMap.put(Input.KEY_ENTER, "ENTER");
		sKeyMap.put(Input.KEY_EQUALS, "EQUALS");
		sKeyMap.put(Input.KEY_F, "F");
		sKeyMap.put(Input.KEY_F1, "F1");
		sKeyMap.put(Input.KEY_F10, "F10");
		sKeyMap.put(Input.KEY_F11, "F11");
		sKeyMap.put(Input.KEY_F12, "F12");
		sKeyMap.put(Input.KEY_F13, "F13");
		sKeyMap.put(Input.KEY_F14, "F14");
		sKeyMap.put(Input.KEY_F15, "F15");
		sKeyMap.put(Input.KEY_F2, "F2");
		sKeyMap.put(Input.KEY_F3, "F3");
		sKeyMap.put(Input.KEY_F4, "F4");
		sKeyMap.put(Input.KEY_F5, "F5");
		sKeyMap.put(Input.KEY_F6, "F6");
		sKeyMap.put(Input.KEY_F7, "F7");
		sKeyMap.put(Input.KEY_F8, "F8");
		sKeyMap.put(Input.KEY_F9, "F9");
		sKeyMap.put(Input.KEY_G, "G");
		sKeyMap.put(Input.KEY_H, "H");
		sKeyMap.put(Input.KEY_HOME, "HOME");
		sKeyMap.put(Input.KEY_I, "I");
		sKeyMap.put(Input.KEY_INSERT, "INSERT");
		sKeyMap.put(Input.KEY_J, "J");
		sKeyMap.put(Input.KEY_K, "K");
		sKeyMap.put(Input.KEY_L, "L");
		sKeyMap.put(Input.KEY_LBRACKET, "LEFT BRACKET");
		sKeyMap.put(Input.KEY_LCONTROL, "LEFT CONTROL");
		sKeyMap.put(Input.KEY_LEFT, "LEFT ARROW");
		sKeyMap.put(Input.KEY_M, "M");
		sKeyMap.put(Input.KEY_MINUS, "MINUS");
		sKeyMap.put(Input.KEY_N, "N");
		sKeyMap.put(Input.KEY_NUMPAD0, "NUMPAD 0");
		sKeyMap.put(Input.KEY_NUMPAD1, "NUMPAD 1");
		sKeyMap.put(Input.KEY_NUMPAD2, "NUMPAD 2");
		sKeyMap.put(Input.KEY_NUMPAD3, "NUMPAD 3");
		sKeyMap.put(Input.KEY_NUMPAD4, "NUMPAD 4");
		sKeyMap.put(Input.KEY_NUMPAD5, "NUMPAD 5");
		sKeyMap.put(Input.KEY_NUMPAD6, "NUMPAD 6");
		sKeyMap.put(Input.KEY_NUMPAD7, "NUMPAD 7");
		sKeyMap.put(Input.KEY_NUMPAD8, "NUMPAD 8");
		sKeyMap.put(Input.KEY_NUMPAD9, "NUMPAD 9");
		sKeyMap.put(Input.KEY_NUMPADENTER, "NUMPAD ENTER");
		sKeyMap.put(Input.KEY_O, "O");
		sKeyMap.put(Input.KEY_P, "P");
		sKeyMap.put(Input.KEY_PERIOD, "PERIOD");
		sKeyMap.put(Input.KEY_Q, "Q");
		sKeyMap.put(Input.KEY_R, "R");
		sKeyMap.put(Input.KEY_RBRACKET, "RIGHT BRACKET");
		sKeyMap.put(Input.KEY_RIGHT, "RIGHT ARROW");
		sKeyMap.put(Input.KEY_S, "S");
		sKeyMap.put(Input.KEY_SPACE, "SPACE");
		sKeyMap.put(Input.KEY_SEMICOLON, "SEMICOLON");
		sKeyMap.put(Input.KEY_SUBTRACT, "SUBTRACT");
		sKeyMap.put(Input.KEY_T, "T");
		sKeyMap.put(Input.KEY_TAB, "TAB");
		sKeyMap.put(Input.KEY_U, "U");
		sKeyMap.put(Input.KEY_UP, "UP ARROW");
		sKeyMap.put(Input.KEY_V, "V");
		sKeyMap.put(Input.KEY_W, "W");
		sKeyMap.put(Input.KEY_X, "X");
		sKeyMap.put(Input.KEY_Y, "Y");
		sKeyMap.put(Input.KEY_Z, "Z");
		
		keyMap.put(Input.KEY_H, "1");
		keyMap.put(Input.KEY_J, "3");
		keyMap.put(Input.KEY_K, "5");
		
		keyString[0] = sKeyMap.get(Input.KEY_H);
		keyString[1] = sKeyMap.get(Input.KEY_J);
		keyString[2] = sKeyMap.get(Input.KEY_K);
		
		currentControls[0] = Input.KEY_H;
		currentControls[1] = Input.KEY_J;
		currentControls[2] = Input.KEY_K;
	}
	
	public void setControls(int key, int oldKey)
	{
		if (keyMap.get(key) == null)
		{
			keyMap.remove(currentControls[oldKey]);
			keyMap.put(key, "" + (oldKey + 1));
			keyString[oldKey] = sKeyMap.get(key);
			currentControls[oldKey] = key;
		}
	}
	
	public HashMap getKeyMapping()
	{
		return keyMap;
	}
	
	public String[] getKeyString()
	{	
		return keyString;
	}
}
