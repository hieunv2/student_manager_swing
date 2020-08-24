package Controller;

public class ValidateInput {

    public Boolean checkEmail(String eamil)
    {
        for(int i=0;i< eamil.length();i++)
            
        {
          // System.out.println(address.charAt(i));
            if (eamil.charAt(i)<48 && eamil.charAt(i)!=46||(eamil.charAt(i)>57&&
                    eamil.charAt(i)<64)||
                   (eamil.charAt(i)>90&&
                    eamil.charAt(i)<97)||
                    eamil.charAt(i)>122)
            {
                return false;
            }
        }
        return true;
    }
    
    public Boolean checkAddress(String address)
    {
        for(int i=0;i< address.length();i++)
            
        {
          // System.out.println(address.charAt(i));
            if (address.charAt(i)<48 && address.charAt(i)!=32 &&address.charAt(i)!=46||(address.charAt(i)>57&&
                    address.charAt(i)<65)||
                   (address.charAt(i)>90&&
                    address.charAt(i)<97)||
                    address.charAt(i)>122)
            {
                return false;
            }
        }
        return true;
    }
    
    public Boolean checkNumber(String number)
    {
        for(int i=0;i< number.length();i++)
            
        {
         //  System.out.println(number.charAt(i));
            if (number.charAt(i)<46 ||
                    number.charAt(i)>57 || number.charAt(i)==47)
            {
                return false;
            }
        }
        return true;
    }
    public Boolean checkName(String name)
    {
        for(int i=0;i< name.length();i++)
        {
            if (name.charAt(i)<65 && name.charAt(i)!=32 ||
                   (name.charAt(i)>90&&
                    name.charAt(i)<97)||
                    name.charAt(i)>122)
            {
                return false;
            } 
        }
        return true;
    }
    public Boolean fomatDate(String date)
    {
        for(int i=0;i< date.length();i++)
        {
            if (date.charAt(i)>57 || 
                    date.charAt(i)<47)
            {
                return false;
            } 
        }
        return true;
    }
}
