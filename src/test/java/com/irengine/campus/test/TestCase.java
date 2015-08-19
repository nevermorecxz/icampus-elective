package com.irengine.campus.test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import org.junit.Test;

import com.irengine.campus.web.rest.util.ReadProperties;

public class TestCase {

	private List<List<String>> data = new ArrayList<List<String>>();

	public void test01() {
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
	}

	@Test
	public void test02() throws IOException {
		System.out.println(ReadProperties.readProperties("numOfSubjects"));
	}

	@Test
	public void test03() throws IOException {
		Properties prop = new Properties();
		FileOutputStream oFile = new FileOutputStream("b.properties", true);// true表示追加打开
		prop.setProperty("phone", "10086");
		prop.store(oFile, "The New properties file");
		oFile.close();
	}
	/**
	 * ELE_SELECT_COURSE
	 */
	@Test
	public void test07(){
		/*SelectCourse:id,selected(true,false),term(学期1为下,0为上),th(第几届的选课),course_id,preferences_id*/
		//insert into ELE_SELECT_COURSE values(40,false,1,2015,4,1);
		//th(第几届的选课)
		int th=2015;
		//针对哪个学期的选课
		int term=0;
		//有多少个学生
		int num=600;
		//可选课程id
		String[] strs1=new String[]{"1","2","3","4","5","6"};
		//可选课程id(可调概率)
		String[] strs=new String[]{"1","1","1","1","2","2","2","2","3","4","5","5","5","5","5","6"};
		//preferences_id
		int preferences_id=1;
		//选几门
		int selectedNum=3;
		Random random = new Random();
		int a=1;
		for(int i=0;i<num;i++){
			List<String> strings=new ArrayList<String>();
			while(strings.size()<selectedNum){
				String str=strs[random.nextInt(strs.length)];
				if(strings.indexOf(str)==-1){
					strings.add(str);
				}
			}
			//System.out.println(strings.toString());
			for(int j=0;j<strs1.length;j++){
				String str="false";
				if(strings.indexOf(""+(j+1))>-1){
					str="true";
				}
				System.out.println("insert into ELE_SELECT_COURSE values("+a+","+str+","+term+","+th+","+strs1[j]+","+preferences_id+");");
				a++;
			}
		}
		//起始学生id
		int studentId=1;
		//结束学生id
		int studentId2=600;
		int b=1;
		for(int i=studentId;i<=studentId2;i++){
			for(int j=0;j<strs1.length;j++){
				//System.out.println("insert into ELE_STUDENTS_COURSES values("+i+","+b+");");
				b++;
			}
		}
	}
	
	@Test
	public void test09(){
		//代课老师对应班级class_id teacher_id
		//insert into ELE_NATURAL_CLASS_TEACHER values(1,1);
		//多少个班
		int num1=12;
		//一个老师带多少个班
		int num2=3;
		//有多少门课程
		int num3=6;
		for(int i=1;i<=num1;i++){
			for(int j=1;j<=num3;j++){
				System.out.println("insert into ELE_NATURAL_CLASS_TEACHER values("+i+","+((i/(num2+1))*6+(j%(num3+1)))+");");
			}
		}
	}
	/**
	 * 生成学生ELE_STUDENTS
	 */
	@Test
	public void test06(){
//		/*学生id,studentnum,user_id,n_class_id,type_id*/
//		insert into ELE_STUDENTS values(9,'200805064202',9,2,1);
		//第几届
		int num=2015;
		//起始id
		int id1=1;
		//结束id
		int id2=600;
		//起始user_id
		int id3=1;
		//学生类型
		String[] strs=new String[]{"1","2"};
		//班级
		String[] strs2=new String[]{"1","2","3","4","5","6","7","8","9","10","11","12"};
		Random random = new Random();
		for(int i=id1;i<=id2;i++){
			String studentnum=i/strs2.length+1<10?0+""+(i/strs2.length+1):i/strs2.length+1+"";
			String strsStr=strs[random.nextInt(strs.length)];
			System.out.println("insert into ELE_STUDENTS values("+i+",'"+num+"05064"+strs2[i%strs2.length]+studentnum+"',"+id3+","+strs2[i%strs2.length]+","+strsStr+");");
			id3++;
		}
	}
	/**
	 * 生成帐号ele_users
	 */
	@Test
	public void test04() {
		//起始id
		int id1=601;
		//结束id
		int id2=624;
		//身份:2=学生,3=老师
		int num=3;
		Random random = new Random();
		for (int i = id1; i <= id2; i++) {
			int gender = random.nextInt(2);
			String genderStr = gender == 0 ? "男" : "女";
			System.out
					.println("insert into ele_users values("
							+ i
							+ ",'"
							+ genderStr
							+ "','"+test05()+"','$2a$10$wHi043xeYDEGgYqbJxdyGenoqTRtQWrDeyZFMFU/p8N6DQ7JS8dYK','num"
							+ i + "',"+num+");");
		}

	}

	@Test
	public void test08(){
		//教师:id,第几届,user_id,course_id
		//insert into ELE_TEACHER values(1,2015,101,1);
		//起始id
		int id1=1;
		//结束id
		int id2=24;
		//第几届
		int th=2015;
		//其实user_id
		int user_id=601;
		//有多少门课
		int num=6;
		
		int num2=0;
		for(int i=id1;i<=id2;i++){
			System.out.println("insert into ELE_TEACHER values("+i+","+th+","+user_id+","+((num2%num)+1)+");");
			user_id++;
			num2++;
		}
	}
	
	
	private String test05() {
		String str="";
		Random fu = new Random();
		Random yu = new Random();
		for (int i = 1; i < 500; i++) {
			int a = fu.nextInt((97) + 25);
			int b = yu.nextInt((97) + 25);
			if ((a == 97) || (a == 101) || (a == 105) || (a == 111)
					|| (a == 117)) {
				if ((b != 97) || (b != 101) || (b != 105) || (b != 111)
						|| (b != 117)) {
					if (b >= 97) {
						str+=(char)b+""+(char)a;
					}
				}
			}
		}
		return str;
	}
}
