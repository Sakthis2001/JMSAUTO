package tests;

import base.BaseTest;
import com.aventstack.extentreports.Status;
import org.lms.listeners.ExtentReportListener;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.ExcelReader;
import utils.ExcelUtils;

import java.io.IOException;
import java.security.PublicKey;
import java.util.List;

import static utils.ExcelReader.ReadExcelData;

public class PreRequestTest extends BaseTest {

    @Test
    public void NavigatetoBaseIcon()
    {
             prerequestpage= homepage.navigatetocommonpage();
             prerequestpage.navigatetobaseicon();
    }

    @Test(priority = 1)
    public void AddPublisher() throws InterruptedException {


        List<Object[]> excelData = ExcelUtils.getExcelData("D:\\uploadtest\\AddArticle.xlsx",0);

        for (Object[] row : excelData) {
            if (row.length == 2) {
                String Pub_acro = row[0].toString();
                String pub_name = row[1].toString();
                prerequestpage.DoAddPub(Pub_acro,pub_name);
            }
            else{
                System.out.println("Row does not have expected numbers: " + row.length);
            }
        }
    }

    @DataProvider(name="getalldata")
    public Object[][] getalldata() throws IOException
    {
        return ExcelReader.ReadExcelData(".//src//test//resources//files//addpublisher.xlsx",13);
    }
    @Test(priority = 1,dataProvider ="getalldata")
    public void AddPublisherWithAllData(String a,String b,String c,String d,String e,String f,String g,String h,String i,String j,String k,String l,String m,String n,String o,String p,String q,String r,String s,String t,String u,String v,String w,String x,String y,String z,String aa,String bb,String cc,String dd,String ee,String ff,String gg,String hh,String ii,String jj,String kk,String ll,String mm,String nn,String oo,String pp,String qq,String rr,String ss) throws InterruptedException
    {
        prerequestpage= homepage.navigatetocommonpage();
        prerequestpage.AddPublisherWithAllData(a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z,aa,bb,cc,dd,ee,ff,gg,hh,ii,jj,kk,ll,mm,nn,oo,pp,qq,rr,ss);

    }


    @Test(priority = 2)
    public void AddJournal() throws InterruptedException {


        List<Object[]> excelData = ExcelUtils.getExcelData("D:\\uploadtest\\AddArticle.xlsx",1);

                for (Object[] row : excelData) {
                    if (row.length == 3) {
                        String Pub = row[0].toString();
                        String J_acrm = row[1].toString();
                        String J_name = row[2].toString();
                        prerequestpage.DoAddJournal(Pub, J_acrm,J_name);
                    }
                    else{
                        System.out.println("Row does not have expected numbers: " + row.length);
                    }
                }

            }



    @DataProvider(name = "getgraphicsuserdata")
    public Object[][] getgraphicspublisherdata() throws IOException
    {
        return ReadExcelData(".//src//test//resources//files//Prerequest.xlsx",0);

    }

    @DataProvider(name = "duplicatedata")
    public Object[][] addduplicateArticle() throws IOException {
        return ExcelReader.ReadExcelData(".//src//test//resources//files//AddArticle.xlsx", 2);
    }

    @Test(priority =3,dataProvider ="duplicatedata", description = "Upon providing valid inputs, Article can be added - need to be listed")
    public void addarticle(String journalacro,String articleid,String artname,String doinum,String workflow) throws InterruptedException {



        prerequestpage= homepage.navigatetocommonpage();
        Boolean isadded= prerequestpage.AddArticleByMandatoryFields(journalacro,articleid,artname,doinum,workflow);
        Assert.assertTrue(isadded,"Article is not added with only the mandatory data");
        ExtentReportListener.getTest().log(Status.INFO, " article is added in article view ");

    }

    @Test(priority = 4,dataProvider ="getgraphicsuserdata")
    public void addUserFunctionality(String name,String employeeid,String designation,String email,String access,String gender,String departmentname,String role)
    {

        prerequestpage= homepage.navigatetocommonpage();
        prerequestpage.adduser(name,employeeid,designation,email,access,gender,departmentname,role);
    }









            @AfterMethod
            public void AfterAllTest()
            {
                prerequestpage.ReloadDashBoard();
            }

//new test



}



