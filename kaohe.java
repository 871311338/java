import java.util.Scanner;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

interface Encryption {   //定义Encryption接口
	public abstract void encrypt(String str, int n);//加密和解密抽象方法
	public abstract void decrypt(String str, int k);
}

public class kaohe {

	private String select1;//封装变量
	private String cleartext;
	private String select2;
	private String select3;

	//getter和setter
	public String getSelect1(){
		return select1;
	}
	public void setSelect1(String select1){
		this.select1 =select1;
	}

	public String getCleartext(){
		return cleartext;
	}
	public void setCleartext(String cleartext){
		this.cleartext =cleartext;
	}
	public String getSelect2(){
		return select2;
	}
	public void setSelect2(String select2){
		this.select2 =select2;
	}
	public String getSelect3(){
		return select3;
	}
	public void setSelect3(String select3){
		this.select3 =select3;
	}

	public static void main(String[] args) {
		System.out.println("请选择加密规则：a为md5加密,b为凯撒加密"); //加密规则信息
		Scanner e=new Scanner(System.in); //键入信息
		String select3 =e.nextLine(); //键入信息读取
		switch (select3){ //根据键入信息选择加密规则
			case "a" : //md5加解密
				System.out.println("[a 加密][b 解密],请选择一个"); //加密解密信息
				Scanner d=new Scanner(System.in); //创建Scanner对象，键入信息
				String select2 =d.nextLine(); //键入信息读取
				switch(select2){ //根据键入信息选择加密或解密
					case "a" : //md5加密    
						System.out.println("请输入明文：");
						Scanner ab=new Scanner(System.in); //创建Scanner对象，键入明文
						String cleartext=ab.nextLine(); //读取键入明文
						Encryption abc2=new Md5Encryption(); //通过接口引用md5加密方法
						abc2.encrypt(cleartext,1); //调用Encrypt方法（md5无密钥，故以1代替键入密钥变量）
						break; //防止case穿透
					case "b" :
						Encryption abc3=new Md5Encryption(); //通过接口引用md5解密
						abc3.decrypt("abc",1); //md5无法解密
						break;
					default : //排除键入信息不是a或b的情况
						System.out.println("对不起，你输入的字符串不是a或b"); 
				}
				break;
			case "b" : //凯撒加解密
				System.out.println("[a 加密][b 解密],请选择一个"); //加密解密信息
				Scanner c=new Scanner(System.in);//	创建Scanner对象，键入信息
				String select1 =c.nextLine();//键入字符串读取
				switch(select1) { //根据键入信息选择加密或解密
					case "a" : //凯撒加密
						System.out.println("请输入明文：");
						Scanner sc=new Scanner(System.in); //创建Scanner对象，键入明文
						String cleartext=sc.nextLine(); //键入明文读取
						System.out.println("请输入密钥:");
						Scanner sc1=new Scanner(System.in); //创建Scanner对象，键入密钥
						int key=sc1.nextInt(); //将键入密钥转换为int类型
						Encryption abc=new CaesarEncryption(); //通过接口引用凯撒加密
						abc.encrypt(cleartext,key); //调用Encrypt方法
						break;
					case "b" : //凯撒解密
						System.out.println("请输入密文:");
						Scanner sc2=new Scanner(System.in); //键入密文
						String s2=sc2.nextLine(); //键入密文读取
						System.out.println("请输入密钥：");
						Scanner sc3=new Scanner(System.in); //键入密钥
						int key1=sc3.nextInt(); //将键入密钥转换为int类型
						Encryption abc1=new CaesarEncryption(); //通过接口引用凯撒解密
						abc1.decrypt(s2,key1);//调用Decrypt方法
						break;
					default : //排除键入信息不是a或b的情况
						System.out.println("对不起，你输入的字符串不是a或b");
				}
				break;
			default : //排除键入信息不是a或b的情况
					System.out.println("对不起，你输入的字符串不是a或b");
		}
	}
}

class CaesarEncryption implements Encryption { //凯撒加密解密

	public void decrypt (String str, int n) {
		//解密方法
		int key=Integer.parseInt("-"+n);//将String型数据转换为Int型数据
		String string="";
		for(int i=0;i<str.length();i++) {
			char letter=str.charAt(i);
			if(letter>='a'&&letter<='z')//如果字符串中的某个字符是小写字母
			{
				letter+=key%26;//移动key除以26的余数位
				if(letter<'a')
					letter+=26;//向左超界
				if(letter>'z')
					letter-=26;//向右超界
			}else if(letter>='A'&&letter<='Z')//如果字符串中的某个字符是大写字母
			{
				letter+=key%26;//移动key除以26的余数位
				if(letter<'A')
					letter+=26;//向左超界
				if(letter>'Z')
					letter-=26;//向右超界
			}
			string +=letter;//将解密后的字符连成字符串
		}
		System.out.println("凯撒解密后为："+string);
	}

	public void encrypt (String str, int k) {
		//凯撒加密
		int key=Integer.parseInt("-"+k);//将String型数据转换为Int型数据
		String string="";
		for(int i=0;i<str.length();i++) {
			char letter=str.charAt(i);
			if(letter>='a'&&letter<='z')//如果字符串中的某个字符是小写字母
			{
				letter+=key%26;//移动key%26位
				if(letter<'a')
					letter+=26;//向左超界
				if(letter>'z')
					letter-=26;//向右超界
			}else if(letter>='A'&&letter<='Z')//如果字符串中的某个字符是大写字母
			{
				letter+=key%26;//移动key%26位
				if(letter<'A')
					letter+=26;//向左超界
				if(letter>'Z')
					letter-=26;//向右超界
			}
			string +=letter;//将解密后的字符连成字符串
		}
		System.out.println("凯撒加密后为："+string);
	}
	

}
class Md5Encryption implements Encryption { //md5加密
	public void encrypt (String src, int l){ //md5加密
        try {
            // 加密对象，指定加密方式
            MessageDigest md5 = MessageDigest.getInstance("md5");
            // 准备要加密的数据
            byte[] b = src.getBytes();
            // 加密
            byte[] digest = md5.digest(b);
            // 十六进制的字符
            char[] chars = new char[] { '0', '1', '2', '3', '4', '5',
				'6', '7' , '8', '9', 'A', 'B', 'C', 'D', 'E','F' };
            StringBuffer sb = new StringBuffer();
            // 处理成十六进制的字符串
            for (byte bb : digest) {
                sb.append(chars[(bb >> 4) & 15]);
                sb.append(chars[bb & 15]);
            }
            // 输出加密后的字符串
            System.out.println(sb);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
	public void decrypt (String str,int u){    //md5解密异常抛出，捕获及处理
		try{
			System.out.println("MD5 算法无法解密"); //异常信息
			throw new UnsupportedOperationException(); //抛出异常
		} catch (UnsupportedOperationException e){ //捕获异常
			e.printStackTrace();
			
		}
		
	}

}