package com.yoyling.utils.jwglxt;

import com.yoyling.domain.Score;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ZFsoft {
	private final String LOGIN_URL="http://jw.xmut.edu.cn/jwglxt/xtgl/login_slogin.html?language=zh_CN&_t=";
	private final String PUBLICKEY_URL="http://jw.xmut.edu.cn/jwglxt/xtgl/login_getPublicKey.html?time=";
	private final String CHECK_SCORE_URL="http://jw.xmut.edu.cn/jwglxt/cjcx/cjcx_cxDgXscj.html?doType=query&gnmkdm=N305005";
	private final String STUDENT_INFORMATION_URL="http://jw.xmut.edu.cn/jwglxt/xsxxxggl/xsxxwh_cxCkDgxsxx.html?gnmkdm=N100801";
	private final String TIMETABLE_URL="http://jw.xmut.edu.cn/jwglxt/kbcx/xskbcx_cxXsKb.html?gnmkdm=N2151";

	private CloseableHttpClient httpClient;
	private BasicCookieStore basicCookieStore;
	public ZFsoft(){
		basicCookieStore=new BasicCookieStore();
		httpClient= HttpClients
				.custom()
				.setDefaultCookieStore(basicCookieStore)
				.build();
	}

	/**
	 * 密码加密 RSA
	 * @param password
	 * @return
	 */
	private String encryp(String password) throws IOException {
		//一、获取 exponent modulus 生成公钥
		String exponent=null,modulus=null;
		HttpGet gpkHttpGet=
				new HttpGet(PUBLICKEY_URL+new Date().getTime());
		gpkHttpGet.setHeader("Accept","application/json, text/javascript, */*; q=0.01");
		gpkHttpGet.setHeader("Accept-Encoding","gzip, deflate");
		gpkHttpGet.setHeader("Accept-Language","zh-CN,zh;q=0.9");
		gpkHttpGet.setHeader("Connection","keep-alive");
		gpkHttpGet.setHeader("Host","jw.xmut.edu.cn");
		gpkHttpGet.setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.131 Safari/537.36");
		gpkHttpGet.setHeader("X-Requested-With","XMLHttpRequest");
		CloseableHttpResponse gpkResponse=null;

			gpkResponse = httpClient.execute(gpkHttpGet);
			if (gpkResponse.getStatusLine().getStatusCode() == 200) {
				String emJson = EntityUtils.toString(gpkResponse.getEntity(), "utf8");
				JSONObject jsonObject = new JSONObject(emJson);
				exponent = jsonObject.getString("exponent");
				modulus = jsonObject.getString("modulus");
			}
			gpkResponse.close();


		//二、根据公钥进行密码加密
		password=RSAEncoder.RSAEncrypt(password,B64.b64tohex(modulus),B64.b64tohex(exponent));
		password=B64.hex2b64(password);
		return password;
	}

	/**
	 * 获取Token
	 * @param timestamp
	 * @return
	 */
	private String crawlCsrfToken(String timestamp){
		String csrftoken=null;
		HttpGet csrftokenHttpGet=
				new HttpGet(LOGIN_URL+timestamp);
		CloseableHttpResponse csrftokenResponse=null;
		try {
			csrftokenResponse = httpClient.execute(csrftokenHttpGet);
			if (csrftokenResponse.getStatusLine().getStatusCode() == 200) {
				Document csrftokenDoc = Jsoup.parse(EntityUtils.toString(csrftokenResponse.getEntity(), "utf8"));
				csrftoken = csrftokenDoc
						.select(".col-sm-4")
						.select(".sl_log_rt")
						.select("input[id=csrftoken]")
						.first()
						.attr("value");
				return csrftoken;
			}
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			try {
				csrftokenResponse.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 模拟登录
	 * @param username 用户名
	 * @param password 密码
	 * @return
	 */
	public ZFsoft login(String username,String password) throws IOException {
		String timestamp=""+new Date().getTime();
		HttpPost loginHttpPost=new HttpPost(LOGIN_URL+timestamp);
		loginHttpPost.setHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3");
		loginHttpPost.setHeader("Accept-Encoding","gzip, deflate");
		loginHttpPost.setHeader("Accept-Language","zh-CN,zh;q=0.9");
		loginHttpPost.setHeader("Cache-Control","max-age=0");
		loginHttpPost.setHeader("Connection","keep-alive");
		loginHttpPost.setHeader("Content-Type","application/x-www-form-urlencoded");
		loginHttpPost.setHeader("Host","jw.xmut.edu.cn");
		loginHttpPost.setHeader("Origin","http://jw.xmut.edu.cn");
		loginHttpPost.setHeader("Upgrade-Insecure-Requests","1");
		loginHttpPost.setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.131 Safari/537.36");
		List<NameValuePair> loginParams=new ArrayList<NameValuePair>();
		password=this.encryp(password);
		String csrftoken=this.crawlCsrfToken(timestamp);
		loginParams.add(new BasicNameValuePair("csrftoken",csrftoken));
		loginParams.add(new BasicNameValuePair("yhm",username));
		loginParams.add(new BasicNameValuePair("mm",password));
		loginParams.add(new BasicNameValuePair("mm",password));
		CloseableHttpResponse loginResponse=null;
		try {
			loginHttpPost.setEntity(new UrlEncodedFormEntity(loginParams, "utf8"));
			loginResponse = httpClient.execute(loginHttpPost);
			List<Cookie>cookies=basicCookieStore.getCookies();
			if(cookies.isEmpty()){
				System.out.println("The Cookie Is None.");
			}else {
				for(Cookie cookie:cookies){

				}
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return this;
	}

	/**
	 * 查看成绩
	 * @param xnm 年份比如2018
	 * @param xqm 学期比如1、或者2、或者3或者不填则为全年
	 * @return
	 */
	public List<Score> checkScore(String xnm, String xqm){
		HttpPost scoreHttpPost=new HttpPost(CHECK_SCORE_URL);
		scoreHttpPost.setHeader("Accept","application/json, text/javascript, */*; q=0.01");
		scoreHttpPost.setHeader("Accept-Encoding","gzip, deflate");
		scoreHttpPost.setHeader("Accept-Language","zh-CN,zh;q=0.9");
		scoreHttpPost.setHeader("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
		scoreHttpPost.setHeader("Host","jw.xmut.edu.cn");
		scoreHttpPost.setHeader("Origin","http://jw.xmut.edu.cn");
		scoreHttpPost.setHeader("Proxy-Connection","keep-alive");
		scoreHttpPost.setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.131 Safari/537.36");
		scoreHttpPost.setHeader("X-Requested-With","XMLHttpRequest");
		List<NameValuePair>scoreParams=new ArrayList<NameValuePair>();
		scoreParams.add(new BasicNameValuePair("xnm",xnm));
		if ("".equals(xqm)) {
			scoreParams.add(new BasicNameValuePair("xqm",""));
		} else {
			scoreParams.add(new BasicNameValuePair("xqm", (String.valueOf((Integer.parseInt(xqm) * Integer.parseInt(xqm) * 3)))));
		}

		scoreParams.add(new BasicNameValuePair("_search","false"));
		scoreParams.add(new BasicNameValuePair("nd",""+new Date().getTime()));
		scoreParams.add(new BasicNameValuePair("queryModel.showCount","100"));
		scoreParams.add(new BasicNameValuePair("queryModel.currentPage","1"));
		scoreParams.add(new BasicNameValuePair("queryModel.sortName",""));
		scoreParams.add(new BasicNameValuePair("queryModel.sortOrder","asc"));
		scoreParams.add(new BasicNameValuePair("time","1"));
		try {
			scoreHttpPost.setEntity(new UrlEncodedFormEntity(scoreParams, "utf8"));
			CloseableHttpResponse scoreResponse = httpClient.execute(scoreHttpPost);
			if (scoreResponse.getStatusLine().getStatusCode() == 200) {
				if (scoreResponse.getEntity() != null) {
					String scoreJson = EntityUtils.toString(scoreResponse.getEntity(), "utf8");

					System.out.println("scoreJson:"+scoreJson);

					JSONObject jsonObject = new JSONObject(scoreJson);

					System.out.println("jsonObject:"+jsonObject);

					JSONArray jsonArray = jsonObject.getJSONArray("items");

					List<Score>scoreList = new ArrayList<>();
					for (int i = 0; i < jsonArray.length(); ++i) {
						JSONObject item = (JSONObject) jsonArray.get(i);
						Score score = new Score();
						score.setXh(item.getString("xh"));
						score.setKch(item.getString("kch"));

						if ("优秀".equals(item.getString("cj"))) {
							score.setCj("95");
						} else if ("良好".equals(item.getString("cj"))) {
							score.setCj("85");
						} else if ("中等".equals(item.getString("cj"))) {
							score.setCj("75");
						} else if ("合格".equals(item.getString("cj"))) {
							score.setCj("65");
						} else{
							score.setCj(item.getString("cj"));
						}


							score.setJd(Float.parseFloat(item.getString("jd")));
							score.setXf(Float.parseFloat(item.getString("xf")));

							scoreList.add(score);
						}
					return scoreList;
				}
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取学生信息
	 * @return
	 */
	public JSONObject getStudentInformation() {
		HttpPost informationHttpPost=new HttpPost(STUDENT_INFORMATION_URL);

		informationHttpPost.setHeader("Accept","application/json, text/javascript, */*; q=0.01");
		informationHttpPost.setHeader("Accept-Encoding","gzip, deflate");
		informationHttpPost.setHeader("Accept-Language","zh-CN,zh;q=0.9");
		informationHttpPost.setHeader("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
		informationHttpPost.setHeader("Host","jw.xmut.edu.cn");
		informationHttpPost.setHeader("Origin","http://jw.xmut.edu.cn");
		informationHttpPost.setHeader("Proxy-Connection","keep-alive");
		informationHttpPost.setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.131 Safari/537.36");
		informationHttpPost.setHeader("X-Requested-With","XMLHttpRequest");

		try {
			CloseableHttpResponse scoreResponse = httpClient.execute(informationHttpPost);
			if (scoreResponse.getStatusLine().getStatusCode() == 200) {
				if (scoreResponse.getEntity() != null) {
					String scoreJson = EntityUtils.toString(scoreResponse.getEntity(), "utf8");

//					System.out.println("scoreJson:" + scoreJson);

					JSONObject jsonObject = new JSONObject(scoreJson);
					return jsonObject;
//					System.out.println("jsonObject:" + jsonObject);
//					System.out.println(jsonObject.getString("bh_id"));

				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}


	/**
	 * 查看课表
	 * @param xnm 年份比如2018
	 * @param xqm 学期比如1、2、3
	 * @return
	 */
	public void checkTimetable(String xnm,String xqm){
		HttpPost timetableHttpPost=new HttpPost(TIMETABLE_URL);
		timetableHttpPost.setHeader("Accept","application/json, text/javascript, */*; q=0.01");
		timetableHttpPost.setHeader("Accept-Encoding","gzip, deflate");
		timetableHttpPost.setHeader("Accept-Language","zh-CN,zh;q=0.9");
		timetableHttpPost.setHeader("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
		timetableHttpPost.setHeader("Host","jw.xmut.edu.cn");
		timetableHttpPost.setHeader("Origin","http://jw.xmut.edu.cn");
		timetableHttpPost.setHeader("Proxy-Connection","keep-alive");
		timetableHttpPost.setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.131 Safari/537.36");
		timetableHttpPost.setHeader("X-Requested-With","XMLHttpRequest");
		List<NameValuePair>timetableParams=new ArrayList<NameValuePair>();
		timetableParams.add(new BasicNameValuePair("xnm",xnm));
		timetableParams.add(new BasicNameValuePair("xqm",xqm));
		try {
			timetableHttpPost.setEntity(new UrlEncodedFormEntity(timetableParams, "utf8"));
			CloseableHttpResponse scoreResponse = httpClient.execute(timetableHttpPost);
			if (scoreResponse.getStatusLine().getStatusCode() == 200) {
				if (scoreResponse.getEntity() != null) {
					String scoreJson = EntityUtils.toString(scoreResponse.getEntity(), "utf8");

					System.out.println("scoreJson:"+scoreJson);

					JSONObject jsonObject = new JSONObject(scoreJson);

					System.out.println("jsonObject:"+jsonObject);

					JSONArray jsonArray = jsonObject.getJSONArray("kbList");

					System.out.println(jsonArray);
				}
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}
