import java.util.Scanner;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

interface Encryption {   //����Encryption�ӿ�
	public abstract void encrypt(String str, int n);//���ܺͽ��ܳ��󷽷�
	public abstract void decrypt(String str, int k);
}

public class kaohe {

	private String select1;//��װ����
	private String cleartext;
	private String select2;
	private String select3;

	//getter��setter
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
		System.out.println("��ѡ����ܹ���aΪmd5����,bΪ��������"); //���ܹ�����Ϣ
		Scanner e=new Scanner(System.in); //������Ϣ
		String select3 =e.nextLine(); //������Ϣ��ȡ
		switch (select3){ //���ݼ�����Ϣѡ����ܹ���
			case "a" : //md5�ӽ���
				System.out.println("[a ����][b ����],��ѡ��һ��"); //���ܽ�����Ϣ
				Scanner d=new Scanner(System.in); //����Scanner���󣬼�����Ϣ
				String select2 =d.nextLine(); //������Ϣ��ȡ
				switch(select2){ //���ݼ�����Ϣѡ����ܻ����
					case "a" : //md5����    
						System.out.println("���������ģ�");
						Scanner ab=new Scanner(System.in); //����Scanner���󣬼�������
						String cleartext=ab.nextLine(); //��ȡ��������
						Encryption abc2=new Md5Encryption(); //ͨ���ӿ�����md5���ܷ���
						abc2.encrypt(cleartext,1); //����Encrypt������md5����Կ������1���������Կ������
						break; //��ֹcase��͸
					case "b" :
						Encryption abc3=new Md5Encryption(); //ͨ���ӿ�����md5����
						abc3.decrypt("abc",1); //md5�޷�����
						break;
					default : //�ų�������Ϣ����a��b�����
						System.out.println("�Բ�����������ַ�������a��b"); 
				}
				break;
			case "b" : //�����ӽ���
				System.out.println("[a ����][b ����],��ѡ��һ��"); //���ܽ�����Ϣ
				Scanner c=new Scanner(System.in);//	����Scanner���󣬼�����Ϣ
				String select1 =c.nextLine();//�����ַ�����ȡ
				switch(select1) { //���ݼ�����Ϣѡ����ܻ����
					case "a" : //��������
						System.out.println("���������ģ�");
						Scanner sc=new Scanner(System.in); //����Scanner���󣬼�������
						String cleartext=sc.nextLine(); //�������Ķ�ȡ
						System.out.println("��������Կ:");
						Scanner sc1=new Scanner(System.in); //����Scanner���󣬼�����Կ
						int key=sc1.nextInt(); //��������Կת��Ϊint����
						Encryption abc=new CaesarEncryption(); //ͨ���ӿ����ÿ�������
						abc.encrypt(cleartext,key); //����Encrypt����
						break;
					case "b" : //��������
						System.out.println("����������:");
						Scanner sc2=new Scanner(System.in); //��������
						String s2=sc2.nextLine(); //�������Ķ�ȡ
						System.out.println("��������Կ��");
						Scanner sc3=new Scanner(System.in); //������Կ
						int key1=sc3.nextInt(); //��������Կת��Ϊint����
						Encryption abc1=new CaesarEncryption(); //ͨ���ӿ����ÿ�������
						abc1.decrypt(s2,key1);//����Decrypt����
						break;
					default : //�ų�������Ϣ����a��b�����
						System.out.println("�Բ�����������ַ�������a��b");
				}
				break;
			default : //�ų�������Ϣ����a��b�����
					System.out.println("�Բ�����������ַ�������a��b");
		}
	}
}

class CaesarEncryption implements Encryption { //�������ܽ���

	public void decrypt (String str, int n) {
		//���ܷ���
		int key=Integer.parseInt("-"+n);//��String������ת��ΪInt������
		String string="";
		for(int i=0;i<str.length();i++) {
			char letter=str.charAt(i);
			if(letter>='a'&&letter<='z')//����ַ����е�ĳ���ַ���Сд��ĸ
			{
				letter+=key%26;//�ƶ�key����26������λ
				if(letter<'a')
					letter+=26;//���󳬽�
				if(letter>'z')
					letter-=26;//���ҳ���
			}else if(letter>='A'&&letter<='Z')//����ַ����е�ĳ���ַ��Ǵ�д��ĸ
			{
				letter+=key%26;//�ƶ�key����26������λ
				if(letter<'A')
					letter+=26;//���󳬽�
				if(letter>'Z')
					letter-=26;//���ҳ���
			}
			string +=letter;//�����ܺ���ַ������ַ���
		}
		System.out.println("�������ܺ�Ϊ��"+string);
	}

	public void encrypt (String str, int k) {
		//��������
		int key=Integer.parseInt("-"+k);//��String������ת��ΪInt������
		String string="";
		for(int i=0;i<str.length();i++) {
			char letter=str.charAt(i);
			if(letter>='a'&&letter<='z')//����ַ����е�ĳ���ַ���Сд��ĸ
			{
				letter+=key%26;//�ƶ�key%26λ
				if(letter<'a')
					letter+=26;//���󳬽�
				if(letter>'z')
					letter-=26;//���ҳ���
			}else if(letter>='A'&&letter<='Z')//����ַ����е�ĳ���ַ��Ǵ�д��ĸ
			{
				letter+=key%26;//�ƶ�key%26λ
				if(letter<'A')
					letter+=26;//���󳬽�
				if(letter>'Z')
					letter-=26;//���ҳ���
			}
			string +=letter;//�����ܺ���ַ������ַ���
		}
		System.out.println("�������ܺ�Ϊ��"+string);
	}
	

}
class Md5Encryption implements Encryption { //md5����
	public void encrypt (String src, int l){ //md5����
        try {
            // ���ܶ���ָ�����ܷ�ʽ
            MessageDigest md5 = MessageDigest.getInstance("md5");
            // ׼��Ҫ���ܵ�����
            byte[] b = src.getBytes();
            // ����
            byte[] digest = md5.digest(b);
            // ʮ�����Ƶ��ַ�
            char[] chars = new char[] { '0', '1', '2', '3', '4', '5',
				'6', '7' , '8', '9', 'A', 'B', 'C', 'D', 'E','F' };
            StringBuffer sb = new StringBuffer();
            // �����ʮ�����Ƶ��ַ���
            for (byte bb : digest) {
                sb.append(chars[(bb >> 4) & 15]);
                sb.append(chars[bb & 15]);
            }
            // ������ܺ���ַ���
            System.out.println(sb);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
	public void decrypt (String str,int u){    //md5�����쳣�׳������񼰴���
		try{
			System.out.println("MD5 �㷨�޷�����"); //�쳣��Ϣ
			throw new UnsupportedOperationException(); //�׳��쳣
		} catch (UnsupportedOperationException e){ //�����쳣
			e.printStackTrace();
			
		}
		
	}

}