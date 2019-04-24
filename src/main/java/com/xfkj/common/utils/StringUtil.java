package com.xfkj.common.utils;

import org.springframework.web.util.HtmlUtils;
import org.springframework.web.util.JavaScriptUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.regex.Pattern;

public class StringUtil {
	public static String CHINESE_BLANK = "　";
	public static String Simplified = "皑蔼碍爱翱袄奥坝罢摆败颁办绊帮绑镑谤剥饱宝报鲍辈贝钡狈备惫绷笔毕毙币闭边编贬变辩辫标鳖别瘪濒滨宾摈饼并拨钵铂驳卜补财参蚕残惭惨灿苍舱仓沧厕侧册测层诧搀掺蝉馋谗缠铲产阐颤场尝长偿肠厂畅钞车彻尘陈衬撑称惩诚骋痴迟驰耻齿炽冲虫宠畴踌筹绸丑橱厨锄雏础储触处传疮闯创锤纯绰辞词赐聪葱囱从丛凑蹿窜错达带贷担单郸掸胆惮诞弹当挡党荡档捣岛祷导盗灯邓敌涤递缔颠点垫电淀钓调迭谍叠钉顶锭订丢东动栋冻斗犊独读赌镀锻断缎兑队对吨顿钝夺堕鹅额讹恶饿儿尔饵贰发罚阀珐矾钒烦范贩饭访纺飞诽废费纷坟奋愤粪丰枫锋风疯冯缝讽凤肤辐抚辅赋复负讣妇缚该钙盖干赶秆赣冈刚钢纲岗皋镐搁鸽阁铬个给龚宫巩贡钩沟构购够蛊顾剐关观馆惯贯广规硅归龟闺轨诡柜贵刽辊滚锅国过骇韩汉号阂鹤贺横轰鸿红后壶护沪户哗华画划话怀坏欢环还缓换唤痪焕涣黄谎挥辉毁贿秽会烩汇讳诲绘荤浑伙获货祸击机积饥讥鸡绩缉极辑级挤几蓟剂济计记际继纪夹荚颊贾钾价驾歼监坚笺间艰缄茧检碱硷拣捡简俭减荐槛鉴践贱见键舰剑饯渐溅涧将浆蒋桨奖讲酱胶浇骄娇搅铰矫侥脚饺缴绞轿较秸阶节茎鲸惊经颈静镜径痉竞净纠厩旧驹举据锯惧剧鹃绢杰洁结诫届紧锦仅谨进晋烬尽劲荆觉决诀绝钧军骏开凯颗壳课垦恳抠库裤夸块侩宽矿旷况亏岿窥馈溃扩阔蜡腊莱来赖蓝栏拦篮阑兰澜谰揽览懒缆烂滥捞劳涝乐镭垒类泪篱离里鲤礼丽厉励砾历沥隶俩联莲连镰怜涟帘敛脸链恋炼练粮凉两辆谅疗辽镣猎临邻鳞凛赁龄铃凌灵岭领馏刘龙聋咙笼垄拢陇楼娄搂篓芦卢颅庐炉掳卤虏鲁赂禄录陆驴吕铝侣屡缕虑滤绿峦挛孪滦乱抡轮伦仑沦纶论萝罗逻锣箩骡骆络妈玛码蚂马骂吗买麦卖迈脉瞒馒蛮满谩猫锚铆贸么霉没镁门闷们锰梦谜弥觅幂绵缅庙灭悯闽鸣铭谬谋亩钠纳难挠脑恼闹馁内拟腻撵捻酿鸟聂啮镊镍柠狞宁拧泞钮纽脓浓农疟诺欧鸥殴呕沤盘庞赔喷鹏骗飘频贫苹凭评泼颇扑铺朴谱栖凄脐齐骑岂启气弃讫牵扦钎铅迁签谦钱钳潜浅谴堑枪呛墙蔷强抢锹桥乔侨翘窍窃钦亲寝轻氢倾顷请庆琼穷趋区躯驱龋颧权劝却鹊确让饶扰绕热韧认纫荣绒软锐闰润洒萨鳃赛三伞丧骚扫涩杀纱筛晒删闪陕赡缮伤赏烧绍赊摄慑设绅审婶肾渗声绳胜圣师狮湿诗尸时蚀实识驶势适释饰视试寿兽枢输书赎属术树竖数帅双谁税顺说硕烁丝饲耸怂颂讼诵擞苏诉肃虽随绥岁孙损笋缩琐锁獭挞抬态摊贪瘫滩坛谭谈叹汤烫涛绦讨腾誊锑题体屉条贴铁厅听烃铜统头秃图涂团颓蜕脱鸵驮驼椭洼袜弯湾顽万网韦违围为潍维苇伟伪纬谓卫温闻纹稳问瓮挝蜗涡窝卧呜钨乌污诬无芜吴坞雾务误锡牺袭习铣戏细虾辖峡侠狭厦吓锨鲜纤咸贤衔闲显险现献县馅羡宪线厢镶乡详响项萧嚣销晓啸蝎协挟携胁谐写泻谢锌衅兴汹锈绣虚嘘须许叙绪续轩悬选癣绚学勋询寻驯训讯逊压鸦鸭哑亚讶阉烟盐严颜阎艳厌砚彦谚验鸯杨扬疡阳痒养样瑶摇尧遥窑谣药爷页业叶医铱颐遗仪彝蚁艺亿忆义诣议谊译异绎荫阴银饮隐樱婴鹰应缨莹萤营荧蝇赢颖哟拥佣痈踊咏涌优忧邮铀犹游诱舆鱼渔娱与屿语吁御狱誉预驭鸳渊辕园员圆缘远愿约跃钥岳粤悦阅云郧匀陨运蕴酝晕韵杂灾载攒暂赞赃脏凿枣灶责择则泽贼赠扎札轧铡闸栅诈斋债毡盏斩辗崭栈战绽张涨帐账胀赵蛰辙锗这贞针侦诊镇阵挣睁狰争帧郑证织职执纸挚掷帜质滞钟终种肿众诌轴皱昼骤猪诸诛烛瞩嘱贮铸筑驻专砖转赚桩庄装妆壮状锥赘坠缀谆着浊兹资渍踪综总纵邹诅组钻";
	public static String Traditional= "皚藹礙愛翺襖奧壩罷擺敗頒辦絆幫綁鎊謗剝飽寶報鮑輩貝鋇狽備憊繃筆畢斃幣閉邊編貶變辯辮標鼈別癟瀕濱賓擯餅並撥缽鉑駁蔔補財參蠶殘慚慘燦蒼艙倉滄廁側冊測層詫攙摻蟬饞讒纏鏟産闡顫場嘗長償腸廠暢鈔車徹塵陳襯撐稱懲誠騁癡遲馳恥齒熾沖蟲寵疇躊籌綢醜櫥廚鋤雛礎儲觸處傳瘡闖創錘純綽辭詞賜聰蔥囪從叢湊躥竄錯達帶貸擔單鄲撣膽憚誕彈當擋黨蕩檔搗島禱導盜燈鄧敵滌遞締顛點墊電澱釣調叠諜疊釘頂錠訂丟東動棟凍鬥犢獨讀賭鍍鍛斷緞兌隊對噸頓鈍奪墮鵝額訛惡餓兒爾餌貳發罰閥琺礬釩煩範販飯訪紡飛誹廢費紛墳奮憤糞豐楓鋒風瘋馮縫諷鳳膚輻撫輔賦複負訃婦縛該鈣蓋幹趕稈贛岡剛鋼綱崗臯鎬擱鴿閣鉻個給龔宮鞏貢鈎溝構購夠蠱顧剮關觀館慣貫廣規矽歸龜閨軌詭櫃貴劊輥滾鍋國過駭韓漢號閡鶴賀橫轟鴻紅後壺護滬戶嘩華畫劃話懷壞歡環還緩換喚瘓煥渙黃謊揮輝毀賄穢會燴彙諱誨繪葷渾夥獲貨禍擊機積饑譏雞績緝極輯級擠幾薊劑濟計記際繼紀夾莢頰賈鉀價駕殲監堅箋間艱緘繭檢堿鹼揀撿簡儉減薦檻鑒踐賤見鍵艦劍餞漸濺澗將漿蔣槳獎講醬膠澆驕嬌攪鉸矯僥腳餃繳絞轎較稭階節莖鯨驚經頸靜鏡徑痙競淨糾廄舊駒舉據鋸懼劇鵑絹傑潔結誡屆緊錦僅謹進晉燼盡勁荊覺決訣絕鈞軍駿開凱顆殼課墾懇摳庫褲誇塊儈寬礦曠況虧巋窺饋潰擴闊蠟臘萊來賴藍欄攔籃闌蘭瀾讕攬覽懶纜爛濫撈勞澇樂鐳壘類淚籬離裏鯉禮麗厲勵礫曆瀝隸倆聯蓮連鐮憐漣簾斂臉鏈戀煉練糧涼兩輛諒療遼鐐獵臨鄰鱗凜賃齡鈴淩靈嶺領餾劉龍聾嚨籠壟攏隴樓婁摟簍蘆盧顱廬爐擄鹵虜魯賂祿錄陸驢呂鋁侶屢縷慮濾綠巒攣孿灤亂掄輪倫侖淪綸論蘿羅邏鑼籮騾駱絡媽瑪碼螞馬罵嗎買麥賣邁脈瞞饅蠻滿謾貓錨鉚貿麽黴沒鎂門悶們錳夢謎彌覓冪綿緬廟滅憫閩鳴銘謬謀畝鈉納難撓腦惱鬧餒內擬膩攆撚釀鳥聶齧鑷鎳檸獰甯擰濘鈕紐膿濃農瘧諾歐鷗毆嘔漚盤龐賠噴鵬騙飄頻貧蘋憑評潑頗撲鋪樸譜棲淒臍齊騎豈啓氣棄訖牽扡釺鉛遷簽謙錢鉗潛淺譴塹槍嗆牆薔強搶鍬橋喬僑翹竅竊欽親寢輕氫傾頃請慶瓊窮趨區軀驅齲顴權勸卻鵲確讓饒擾繞熱韌認紉榮絨軟銳閏潤灑薩鰓賽叁傘喪騷掃澀殺紗篩曬刪閃陝贍繕傷賞燒紹賒攝懾設紳審嬸腎滲聲繩勝聖師獅濕詩屍時蝕實識駛勢適釋飾視試壽獸樞輸書贖屬術樹豎數帥雙誰稅順說碩爍絲飼聳慫頌訟誦擻蘇訴肅雖隨綏歲孫損筍縮瑣鎖獺撻擡態攤貪癱灘壇譚談歎湯燙濤縧討騰謄銻題體屜條貼鐵廳聽烴銅統頭禿圖塗團頹蛻脫鴕馱駝橢窪襪彎灣頑萬網韋違圍爲濰維葦偉僞緯謂衛溫聞紋穩問甕撾蝸渦窩臥嗚鎢烏汙誣無蕪吳塢霧務誤錫犧襲習銑戲細蝦轄峽俠狹廈嚇鍁鮮纖鹹賢銜閑顯險現獻縣餡羨憲線廂鑲鄉詳響項蕭囂銷曉嘯蠍協挾攜脅諧寫瀉謝鋅釁興洶鏽繡虛噓須許敘緒續軒懸選癬絢學勳詢尋馴訓訊遜壓鴉鴨啞亞訝閹煙鹽嚴顔閻豔厭硯彥諺驗鴦楊揚瘍陽癢養樣瑤搖堯遙窯謠藥爺頁業葉醫銥頤遺儀彜蟻藝億憶義詣議誼譯異繹蔭陰銀飲隱櫻嬰鷹應纓瑩螢營熒蠅贏穎喲擁傭癰踴詠湧優憂郵鈾猶遊誘輿魚漁娛與嶼語籲禦獄譽預馭鴛淵轅園員圓緣遠願約躍鑰嶽粵悅閱雲鄖勻隕運蘊醞暈韻雜災載攢暫贊贓髒鑿棗竈責擇則澤賊贈紮劄軋鍘閘柵詐齋債氈盞斬輾嶄棧戰綻張漲帳賬脹趙蟄轍鍺這貞針偵診鎮陣掙睜猙爭幀鄭證織職執紙摯擲幟質滯鍾終種腫衆謅軸皺晝驟豬諸誅燭矚囑貯鑄築駐專磚轉賺樁莊裝妝壯狀錐贅墜綴諄著濁茲資漬蹤綜總縱鄒詛組鑽";
	
	public StringUtil() {
		super();
	}
	
	public interface TransConditions {
		public boolean whether(StringBuffer sb, int index, int count, char dst, char src);
		public void end(StringBuffer sb);
		public void each(StringBuffer sb, int index, char text, boolean isChinese);
	}
	
	public static void tranChs(StringBuffer sb, boolean converToTraditional, TransConditions tc) {
		int i = 0;
		int len = sb.length();
		int index;
		char letter;
		int code;
		int count = 0;
		boolean isChinese;
		String src, des;
		char tmp;
		
		if( converToTraditional ){
			src = Simplified;
			des = Traditional;
		}else{
			src = Traditional;
			des = Simplified;
		}
				
		for (; i < len; i++) {
		    letter = sb.charAt(i);
		    isChinese = isChinese(letter);
		    
		    //like Array.map
		    tc.each(sb, i, letter, isChinese);
		
		    if (!isChinese) {
		        continue;
		    }
		
		    index = src.indexOf(letter);
		
		    if (index != -1) {
		    	count++;
		    	tmp = des.charAt(index);
		    	if( tc.whether(sb, i, count, tmp, letter) ){
		    		sb.setCharAt(i, tmp);
		    	}
		    }
		}
		
		tc.end(sb);
	}
	
	public static boolean isChinese(char c){
		return (c > 0x3400 && c < 0x9FC3) || (c > 0xF900 && c < 0xFA6A);
	}
	
	//trans all;
	public static void tranChs(StringBuffer str, boolean toT){
		tranChs(str, toT, new TransConditions() {
			@Override
			public boolean whether(StringBuffer sb, int index, int count, char dst, char src) {
				return true;
			}
			@Override
			public void end(StringBuffer sb) {}
			@Override
			public void each(StringBuffer sb, int a, char t, boolean i) {}
		});
	}
	
	public static byte double2Byte(double d) {
		byte i = 0;
		try {
			String str = "" + d;
			i = Byte.parseByte(str.substring(0, str.indexOf(".")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}
	
	public static double byte2double(byte b) {
		double i = 0;
		try {
			i = Double.parseDouble("" + b);
		} catch (Exception e) {
		}
		return i;
	}

	public static int str2Int(String str, int d) {
		int i = 0;
		if (str == null) {
			return d;
		} else {

			try {
				i = Integer.parseInt(str);
			} catch (Exception e) {
				return d;
			}
		}
		return i;
	}
	
	public static String toHexString(String s) {
		String str = "";
		for (int i = 0; i < s.length(); i++){ 
			int ch = (int)s.charAt(i); 
			String s4 = Integer.toHexString(ch);
			str = str + s4; 
		} 
		return str; 
	}
	
	public static int char2HexNumber(char s){
		return Integer.valueOf(String.valueOf(s), 16);
	}

	/**
	 * string to int
	 * 
	 * @param str
	 * @return 0 if str is null or catch exception
	 */
	public static int str2Int(String str) {
		int i = 0;
		if (str == null) {
			return 0;
		} else {

			try {
				i = Integer.parseInt(str);
			} catch (Exception e) {
				return -1;
			}
		}
		return i;
	}

	public static double str2Double(String str) {
		double i = 0;
		if (str == null) {
			return 0;
		} else {

			try {
				i = Double.parseDouble(str);
			} catch (Exception e) {
				return -1;
			}
		}
		return i;
	}
	
	public static int str2Int0(String str) {
		return str2Int(str, 0);
	}

	/**
	 * spring处理转义的方法
	 * 
	 * @param str
	 * @return
	 * @return
	 */
	public static String htmlEscape(String str) {
		return HtmlUtils.htmlEscape(str);
	}

	/**
	 * spring 处理js特殊字符的方法
	 * 
	 * @param str
	 * @return
	 */
	public static String javaScriptEscape(String str) {
		return JavaScriptUtils.javaScriptEscape(str);
	}


	/**
	 * gts
	 * 
	 * @param str
	 * @return
	 */
	public static String escapeHtml(String str) {
		if (str == null)
			return null;
		char c;
		StringBuffer sb = new StringBuffer();
		int length = str.length();
		for (int i = 0; i < length; i++) {
			c = str.charAt(i);
			if (c == '<')
				sb = sb.append("&lt;");
			else if (c == '>')
				sb = sb.append("&gt;");
			else if (c == '&')
				sb = sb.append("&amp;");
			else if (c == '"')
				sb = sb.append("&quot;");
			else
				sb = sb.append(c);

		}
		return sb.toString();

	}

	/**
	 * 从别的项目里copy的代码
	 * 
	 * @param input
	 * @return
	 */
	public static String sqlQuote(String input) {
		if (input == null || input.length() == 0)
			return input;
		StringBuffer buf = new StringBuffer(input.length() + 6);
		char ch = ' ';
		for (int i = 0; i < input.length(); i++) {
			ch = input.charAt(i);
			if (ch == '\'')
				buf.append("''");
			else if (ch == '"')
				buf.append("\\\"");
			else if (ch == '\\')
				buf.append("\\\\");
			else
				buf.append(ch);
		}

		return buf.toString();
	}

	/**
	 * 如果为空或者空字符串返回false,非空返回true
	 * 
	 * @param str
	 * @return
	 */
	public static boolean strNotNull(String str) {
		if (str == null || str.trim().length() == 0
				|| str.replaceAll("　", "").length() == 0)
			return false;
		else
			return true;
	}
	
	public static boolean strIsNull(String str) {
		return !StringUtil.strNotNull(str);
	}

	public static boolean strNotNull(HttpServletRequest request, String str) {
		str = request.getParameter(str);
		if (str == null || str.trim().length() == 0
				|| str.replaceAll("　", "").length() == 0)
			return false;
		else
			return true;
	}

	public static String showSelect(Object a, Object b) {
		if (a == null || b == null)
			return "";
		else if (a.toString().trim().equals(b.toString().trim()))
			return " selected ";
		else
			return "";

	}


	public static String getParameter(HttpServletRequest request, String name,
                                      String rs) {
		if (name == null)
			return rs;
		String result = request.getParameter(name);
		if (result == null)
			return rs;
		return result;
	}

	/**
	 * length =0的时候小数点后1位,为0,[2.1->2.0] -1的时候个位小数点后1位为0[21.323->20.0]，以此类推
	 * 
	 * @param d
	 * @param length
	 * @return
	 */
	public static double get4she5ru(double d, int length) {
		BigDecimal b = new BigDecimal(d);
		return b.setScale(length, BigDecimal.ROUND_HALF_UP).doubleValue();

	}

	public static String getClassName(Object o) {
		String name = o.getClass().toString();
		name = name.replace("class ", "");
		String str[] = name.split("\\.");
		name = str[str.length - 1];

		return name;

	}

	public static byte str2Byte0(String p) {
		return StringUtil.str2Byte(p,(byte)0);
	}
	public static byte str2Byte(String p, byte b) {
		try {
			return Byte.parseByte(p);
		} catch (Exception e) {
			return b;
		}
	}
	
	public static String removeHtml(String inputString) {
		if(inputString == null)
			return null;
		String htmlStr = inputString; // 含html标签的字符串
		String textStr = "";
		java.util.regex.Pattern p_script;
		java.util.regex.Matcher m_script;
		java.util.regex.Pattern p_style;
		java.util.regex.Matcher m_style;
		java.util.regex.Pattern p_html;
		java.util.regex.Matcher m_html;

		try {
			String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; // 定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script>
																										// }
			String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; // 定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style>
																									// }
			String regEx_html = "<[^i][^>]*>"; // 定义HTML标签的正则表达式

			p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
			m_script = p_script.matcher(htmlStr);
			htmlStr = m_script.replaceAll(""); // 过滤script标签

			p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
			m_style = p_style.matcher(htmlStr);
			htmlStr = m_style.replaceAll(""); // 过滤style标签

			p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
			m_html = p_html.matcher(htmlStr);
			htmlStr = m_html.replaceAll(""); // 过滤html标签

			htmlStr = htmlStr.replaceAll("[ \\u3000\\t\\r]", "");
			htmlStr = htmlStr.trim();
			htmlStr = htmlStr.replaceAll("[\\n]+", "\r\n\r\n\u3000\u3000");
			htmlStr = "\u3000\u3000" + htmlStr;
			textStr = htmlStr;

		} catch (Exception e) {
			System.err.println("Html2Text: " + e.getMessage());
		}

		return textStr;// 返回文本字符串
	}

	public static String removeResouce(String inputString) {
		if(inputString == null)
			return null;
		String htmlStr = inputString; // 含html标签的字符串
		String textStr = "";
		java.util.regex.Pattern p_html;
		java.util.regex.Matcher m_html;

		try {
			String regEx_html = "<[^>]*>"; // 定义HTML标签的正则表达式

			p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
			m_html = p_html.matcher(htmlStr);
			htmlStr = m_html.replaceAll(""); // 过滤html标签
			textStr = htmlStr;

		} catch (Exception e) {
			System.err.println("Html2Text: " + e.getMessage());
		}
		return textStr;// 返回文本字符串
	}
	
	public static String removeNRT(String text){
		String content = text.replaceAll("[\\n\\r]", "");
		content = content.replaceAll("[\\t]", "");
		return content;
	}
	public static long str2Long(String str){
		long i = 0;
		if (str == null) {
			return 0;
		} else {

			try {
				i = Long.parseLong(str);
			} catch (Exception e) {
				return -1;
			}
		}
		return i;
	}
	
	public static void test () {
		String notifyMsg = "301310063009501|41|1.00|CNY|20131105| |20121008|193033|05F5E5E3|1|55.00|0|6222600910068617305| |119.253.38.246|113.31.27.22| |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";
		int lastIndex = notifyMsg.lastIndexOf("|");
		String signMsg = notifyMsg.substring(lastIndex + 1, notifyMsg.length()); // 获取签名信息
		String srcMsg = notifyMsg.substring(0, lastIndex + 1);
		System.out.println("signMsg : " + signMsg);
		System.out.println("srcMsg : " + srcMsg);
		
		String[] src = srcMsg.split("\\|");
		String merchno = src[0];	//商户客户号
		String order_no = src[1];	//第二位为订单号
		String amount = src[2];	//第三位为交易金额
		String trancurrtype = src[3];	//第四位为交易币种
		String traDate = src[6] + src[7];
		
		System.out.println("traDate:" + traDate);
		String trade_no = src[8];	//交易流水号
		String result = src[9];		//交易结果 1成功
		String feesum = src[10];	//手续费总额
		String errdis = src[13];	//错误信息
		String referer = src[15];	//跳转前的域名
		
	}
	
	public static void main(String args[]) {
//		String str= "\n\r\u3000\u3000abcdefg \n\r \u3000 \u3000          ";
//		System.out.println(str2Double("0"));
		try {
			//%E7%A3%A8%E9%93%81%E5%B8%81
			//
//			System.out.println(new String("800%C4%A5%CC%FA%B1%D2".getBytes("gb2312"), Charset.forName("utf8")));
			System.out.println(URLDecoder.decode("800%C4%A5%CC%FA%B1%D2", "gb2312"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		test();
	}
	
	/**
	 * 替换中英文空格
	 * @param str
	 * @return
	 */
	public static String replaceNBSP(String str) {
		if(str == null)
			return null;
		else
			return str.replaceAll(" ", "").replaceAll("　", "");
	}

	public static byte int2Byte(int value) {
		if (value > Byte.MAX_VALUE || value < Byte.MIN_VALUE)
			return -1;
		return (byte) value;
	}
}
