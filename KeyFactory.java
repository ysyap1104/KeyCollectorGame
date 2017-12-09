public class KeyFactory{
//Jeffrey Tan Kah Jun
    public Key makeKey(String newKeyType){        //Jeffrey Tan Kah Jun

        if (newKeyType.equals("Pinkey")){         //If new key type is equal to Pinkey, then return as new Pinkey
            return new Pinkey();
        } else
        if (newKeyType.equals("Donkey")){		  //If new key type is equal to Pinkey, then return as new Pinkey
            return new Donkey();
        } else
        if (newKeyType.equals("KeyDisk")){		  //If new key type is equal to Pinkey, then return as new Pinkey
            return new KeyDisk();
        } else 
		if(newKeyType.equals("KeyNote")){		  //If new key type is equal to Pinkey, then return as new Pinkey
			return new KeyNote();
		} else
		if(newKeyType.equals("Monkey")){		  //If new key type is equal to Pinkey, then return as new Pinkey
			return new Monkey();
		} else
			return null;
    }

     

}