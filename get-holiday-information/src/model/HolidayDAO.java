package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class HolidayDAO {

	public static ArrayList<HolidayVO> webConnection(/*String year, String month*/) {
		ArrayList<HolidayVO> list = new ArrayList<>();
		StringBuilder urlBuilder = new StringBuilder(
				"http://apis.data.go.kr/B090041/openapi/service/SpcdeInfoService/getRestDeInfo"); /* URL */
		try {
			urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=����Ű");
			urlBuilder.append(
					"&" + URLEncoder.encode("solYear", "UTF-8") + "=" + URLEncoder.encode("2015", "UTF-8")); /* �� */
			urlBuilder.append(
					"&" + URLEncoder.encode("solMonth", "UTF-8") + "=" + URLEncoder.encode("09", "UTF-8")); /* �� */
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} /* Service Key */

		URL url = null;
		HttpURLConnection conn = null;
		try {
			url = new URL(urlBuilder.toString());
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-type", "application/json");
			System.out.println("Response code: " + conn.getResponseCode());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		BufferedReader rd=null;
		try {
			if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
				rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			} else {
				rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
			}
			Document doc = parseXML(conn.getInputStream());
			// a. field �±װ�ü ������� �����´�.
			NodeList descNodes = doc.getElementsByTagName("item");
			// b. List��ü ����
			
			// c. �� item �±��� �ڽ��±׿��� ���� ��������
			for (int i = 0; i < descNodes.getLength(); i++) {
				// item
				Node item = descNodes.item(i);
				HolidayVO data = new HolidayVO();
				// item �ڽ��±׿� ���������� ����
				for (Node node = item.getFirstChild(); node != null; 
					node =node.getNextSibling()) {
//					System.out.println(node.getNodeName() + " : " +node.getTextContent());

					switch (node.getNodeName()) {
					case "locdate":
						data.setLocdate(Integer.parseInt(node.getTextContent()));
						break;
					case "dateKind":
						data.setDateKind(node.getTextContent());
						break;
					case "isHoliday":
						data.setIsHoliday(node.getTextContent());
						break;
					case "dateName":
						data.setDateName(node.getTextContent());
						System.out.println(node.getTextContent()+"�ε� �Ϸ�");
						break;
					}
				}
				// d. List��ü�� �߰�
				list.add(data);
			}
//			// e.����Ȯ��
//			for (FoundArticle d : list) {
//			System.out.println(d);
//			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				rd.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		conn.disconnect();
		return list;
	}
	
	public static Document parseXML(InputStream stream) {
		DocumentBuilderFactory objDocumentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder objDocumentBuilder = null;
		Document doc = null;
		try {
			objDocumentBuilder = objDocumentBuilderFactory.newDocumentBuilder();
			doc = objDocumentBuilder.parse(stream);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) { // Simple API for XML e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return doc;
	}

	public static void insert(ArrayList<HolidayVO> list) {
		//�����ϱ� ���� ���̺� �ִ� ������ ����
		HolidayManager.deleteArticle();
				
				Connection con = null;
				PreparedStatement pstmt = null;
				try {
					con = controller.DBUtil.makeConnection();
					for (HolidayVO data : list) {
						String sql = "insert into holiday values(?,holiday_seq.nextval,?,?,?,?)";
						pstmt = con.prepareStatement(sql);
						pstmt.setInt(1, data.getLocdate());
						
						pstmt.setString(2, data.getDateKind());
						pstmt.setString(3, data.getIsHoliday());
						pstmt.setString(4, data.getDateName());
						pstmt.setString(5, data.getMemo());
						int value = pstmt.executeUpdate();

						if (value == 1) {
							System.out.println(data.getDateName() + "��� �Ϸ�");
						} else {
							System.out.println(data.getDateName() + "��� ����");
						}
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					try {
						if (pstmt != null) {
							pstmt.close();
						}
						if (con != null) {
							con.close();
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}

				}

	}

	public static ArrayList<HolidayVO> select() {
		// TODO Auto-generated method stub
		return null;
	}

	public static void search() {
		// TODO Auto-generated method stub

	}

}
