package com.springmvc.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.kafka.clients.consumer.ConsumerRebalanceListener;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.springmvc.entity.Black;
import com.springmvc.entity.Camera;
import com.springmvc.entity.Danger_level;
import com.springmvc.entity.Danger_rank;
import com.springmvc.entity.People;
import com.springmvc.entity.Person;
import com.springmvc.entity.Point2;
import com.springmvc.entity.Targets;
import com.springmvc.entity.Temp;
import com.springmvc.entity.User;
import com.springmvc.kafka.KafkaUtil;
import com.springmvc.service.CameraService;
import com.springmvc.service.Danger_levelService;
import com.springmvc.service.Danger_rankService;
import com.springmvc.service.EventService;
import com.springmvc.service.Neo4jService;
import com.springmvc.service.PeopleService;
import com.springmvc.service.PersonService;
import com.springmvc.service.PersonService2;
import com.springmvc.service.Target_infoService;
import com.springmvc.service.UserService;
import com.springmvc.service.UserService2;
import com.springmvc.test.TestUnicode;
import com.springmvc.util.DateUtil;
import com.springmvc.util.Util;
 
@Controller
public class ViewController {
 
    private Logger logger = Logger.getLogger(ViewController.class);
 
    @Resource
    private UserService userService;
    
    @Resource
    private UserService2 userService2;
    
    @Resource
    private Neo4jService neo4jService;
    
    @Resource
    private PersonService personService;
    
    @Resource
    private PersonService2 personService2;
    
    @Resource
    private EventService eventService;
    
    @Resource
    private CameraService cameraService;
    
    @Resource
    private Danger_levelService danger_levelService;
    
    @Resource
    private Target_infoService target_infoService;
    
    @Resource
    private Danger_rankService danger_rankService;
    
    @Resource
    private PeopleService peopleService;
    
    //经过多边形过滤后，摄像头的坐标
    List<Temp> filterList=null;
    
    //绘制多边形的点
    List<Point2> point2list=null;
    
    //是否已经根据名字查询
    boolean isNameUsed=false;
    //是否已经根据事件查询
    boolean isEventUsed=false;
    ThreadLocal<KafkaConsumer<String, String>> tl;
    KafkaConsumer<String, String> kafkaConsumer ;
    List<String> topicList=new ArrayList<String>();
    
    String topic ="PicTest1282";
    List<Temp> oldMessageList=new ArrayList<Temp>();
    List<Temp> newMessageList=new ArrayList<Temp>();
    
    //请求findByMultipleEvent
    boolean flag=false;
    
    int count=0;
    
    @RequestMapping("/lines-bmap-bus")
    public String index(){
 
    	 System.out.println("/effectScatter-bmap。。。。");
         logger.info("/effectScatter-bmap。。。。");
  
         return "pages/lines-bmap-bus.html";
    }
 
    @RequestMapping("/login")
    public String find(String name,String password){
 
    	logger.info("你已通过springMVC进入controller方法。。。。");
        System.out.println("后台得到的："+name+","+password);
        User loginuser = userService.findByUsernameAndPwd(name,password);
        if(loginuser != null){
        	return "pages/success.html";
        }else {
        	return "pages/fail.html";
        }
    }
 
    @RequestMapping("/effectScatter-bmap")
    public String success(){
        System.out.println("/effectScatter-bmap。。。。");
        logger.info("/effectScatter-bmap。。。。");
 
        return "pages/effectScatter-bmap.html";
    }
    
    @RequestMapping("/get-lines-bus-json")
    public String get(){
        System.out.println("/get-lines-bus-json。。。。");
        logger.info("/get-lines-bus-json。。。。");
 
        return "data/lines-bus.json";
    }
    
    
    @RequestMapping("/neo4j")
    public String neo4j(){
        System.out.println("/neo4j。。。。");
        logger.info("/neo4j。。。。");
        
       String s= neo4jService.findByUsernameAndPwd("", "");
 
       if (s!=null) {
		return "pages/success.html";
	} else {
		return "pages/fail.html";
	}
        
    }
    
    @RequestMapping("/baidumap")
    public String baidumap(){
    	System.out.println("/baidumap。。。。");
    	logger.info("/baidumap。。。。");
    	
//    	String s= neo4jService.findByUsernameAndPwd("", "");
    	
//    	if (s!=null) {
    		return "baidu_offline/map.html";
//    	} else {
//    		return "pages/fail.html";
//    	}
    	
    }
    
    @RequestMapping("/baidu_offline_test")
    public String baidu_offline_test(@RequestParam int camId,HttpServletRequest request,Model model){
    	logger.info("/baidu_offline_test。。。。"+camId);
    	List<Person> list=personService.find(camId);
    	logger.info("baidu_offline_test list:"+list);
    	model.addAttribute("list", list);
    	return "pages/baidu_offline_test.jsp";
    	
    }
    
    @RequestMapping("/baidu_offline_test2")
    public String baidu_offline_test2(){
    	System.out.println("/baidu_offline_test2。。。。");
    	logger.info("/baidu_offline_test2。。。。");
    	
    	return "pages/effectScatter-bmap.html";
    	
    }
    
    @RequestMapping("/baidu_offline_video")
    public String baidu_offline_video(HttpServletRequest request,Model model){
    	System.out.println("/baidu_offline_video。。。。");
    	logger.info("/baidu_offline_video。。。。");
    	List<Person> list=personService.find(1);
    	logger.info("baidu_offline_video list:"+list);
    	model.addAttribute("list", list);
    	return "pages/baidu_offline_video.jsp";
    	
    }
    
    
    @RequestMapping("/login2")
    public String find2(String name,String password){
 
    	logger.info("你已通过springMVC进入controller find2方法。。。。");
        System.out.println("后台得到的find2："+name+","+password);
        User loginuser = userService2.findByUsernameAndPwd(name,password);
        if(loginuser != null){
        	return "pages/success.html";
        }else {
        	return "pages/fail.html";
        }
    }
    
    
    
    
    
    @RequestMapping("/testHttp")
    @ResponseBody
    public Map<String, String> testHttp(@RequestBody Map<String, String> map){
    	
    	logger.info("你已通过springMVC进入controller testHttp方法。。。。");
    	String name=map.get("name");
    	String password =map.get("id");
    	logger.info("后台得到的testHttp："+name+","+password);
    	Map<String, String> map2 = new HashMap<String, String>();
        map2.put("id", "334");
        map2.put("name", "fsdf");
        return map2;
    }
    
    
    @RequestMapping("/test-requestbody2")
    @ResponseBody
    public Map<String, String> testrequestbody2(@RequestBody Map<String, String> map){
    	System.out.println("/test-requestbody2。。。。");
    	logger.info("/test-requestbody2。。。。");
    	Integer id=Integer.parseInt(map.get("id"));
    	String name=map.get("name");
    	String price =map.get("price");
    	System.out.println("id:"+id+","+name+","+price);
    	
    	Map<String, String> map2 = new HashMap<String, String>();
        map2.put("id", "334");
        map2.put("name", "fsdf");
        return map2;
    	
    	
    }
    
    @RequestMapping("/testforward")
    public String testforward(@RequestBody Map<String, String> map,HttpServletRequest request){
    	
    	logger.info("你已通过springMVC进入controller testforward方法。。。。");
    	String name=map.get("name");
    	String password =map.get("id");
    	logger.info("后台得到的testforward："+name+","+password);
    	Map<String, String> map2 = new HashMap<String, String>();
        map2.put("id", "334");
        map2.put("name", "fsdf");
        request.setAttribute("map", 1);
        return "redirect:/baidu_offline_test2";
    }
    
    @RequestMapping("/test-RequestParam")
    public String testRequestParam(@RequestParam String name,@RequestParam int id,HttpServletRequest request,Model model){
    	
    	logger.info("你已通过springMVC进入controller RequestParam方法。。。。");
    	
    	logger.info("后台得到的RequestParam："+name+","+id);
    	Map<String, String> map2 = new HashMap<String, String>();
    	map2.put("id", "334");
    	map2.put("name", "fsdf");
//    	request.setAttribute("map", 1);
    	model.addAttribute("map", map2);
//    	return "redirect:/test-jsp";
    	return test(model);
    }
    
    /**
     * 测试redirect后前台取值
     * LANG
     * @return
     */
    @RequestMapping("/test-jsp")
    public String test(Model model){
    	System.out.println("/test-jsp。。。。");
    	logger.info("/test-jsp。。。。");
//    	System.out.println(request.getAttribute("map"));;
    	return "pages/test.jsp";
    	
    }
 
    @RequestMapping("/findByName")
    public String findByName(@RequestParam String name,@RequestParam long start,@RequestParam long end,Model model){
//    	System.out.println("/findByName。。。。");
    	logger.info("/findByName。。。。"+name+","+start+","+end);
    	List<Temp> list=null;
    	if (this.filterList!=null) {
//			if (isNameUsed==false) {
				
    			logger.info("findByName---filterList:"+this.filterList.toString());
				Stream<String> caStream=this.filterList.stream().map(e->(e.getCamera_id()));
				List<String> canmerIds=caStream.collect(Collectors.toList());
				list=personService2.findByNameAndCamerId(name, start, end, canmerIds);
				isNameUsed=true;
				model.addAttribute("point2list", point2list);
//			}
			if (isEventUsed&&isNameUsed) {
				
				this.filterList=null;
			}
		}else {
			
			list=personService2.findByName(name, start, end);
		}
    	
    	List<Temp> outList=list.stream().map((e)->{
    		
    		Temp t=new Temp();
    		t.setPic_path(e.getPic_path().replace("\\", "/").replace("//192.168.1.130", "http://192.168.1.130:8080"));
    		t.setLatitude(e.getLatitude());
    		t.setLongitude(e.getLongitude());
    		t.setAge(e.getAge());
    		t.setGender(e.getGender());
    		t.setName(e.getName());
    		t.setTimestampStr(DateUtil.timeStamp2Date(String.valueOf(e.getTimestamp()),null));
    		t.setGender(e.getGender());
    		t.setCatalog(0);
    		return t;
    	}).collect(Collectors.toCollection(ArrayList::new));
    	model.addAttribute("list", outList);
    	return "pages/map.jsp";
    	
    }
    
    @RequestMapping("/findByEvent")
    public String findByEvent(@RequestParam String type,@RequestParam long start,@RequestParam long end,Model model){
    	System.out.println("/findByEvent。。。。"+type+","+start+","+end);
    	logger.info("/findByEvent。。。。");
    	
    	List<Temp> list=null;
    	if (this.filterList!=null) {
//			if (isEventUsed==false) {
				
				System.out.println("findByName---filterList:"+this.filterList.toString());
				Stream<String> caStream=this.filterList.stream().map(e->(e.getCamera_id()));
				List<String> canmerIds=caStream.collect(Collectors.toList());
				list=eventService.findByTypeAndCamerId(type, start, end, canmerIds);
				isEventUsed=true;
				model.addAttribute("point2list", point2list);
//			}
			if (isEventUsed&&isNameUsed) {
				
				this.filterList=null;
			}
		}else {
			
			list=eventService.findByType(type, start, end);
		}
    	
    	List<Temp> outList=list.stream().map((e)->{
    		
    		Temp t=new Temp();
    		t.setPic_path(e.getPic_path());
    		t.setLatitude(e.getLatitude());
    		t.setLongitude(e.getLongitude());
    		t.setDescribe(e.getDescribe());
    		t.setStart_timeStr(DateUtil.timeStamp2Date(String.valueOf(e.getStart_time()),null));
    		t.setEnd_timeStr(DateUtil.timeStamp2Date(String.valueOf(e.getEnd_time()),null));
    		t.setCatalog(1);
    		return t;
    	}).collect(Collectors.toCollection(ArrayList::new));
    	System.out.println("outlist:"+outList);
    	model.addAttribute("list", outList);
    	return "pages/map.jsp";
    	
    }
    
    @RequestMapping("/initTopic")
    public void initTopic(){
    	if (count>0) {
    		logger.info("initTopic count:"+count);
			this.topicList.clear();
			this.kafkaConsumer.close();
			this.kafkaConsumer=null;
			this.tl.remove();
			logger.info("第二次请求，清空topic和消费者");
		}
    	List<String>  topicList=new ArrayList<>();
    	topicList.add(topic);
    	logger.info("topic:"+topicList);
//    	kafkaConsumer = new KafkaConsumer<String, String>(KafkaUtil.getKafkaConsumerProp(true));
    	tl=new ThreadLocal<KafkaConsumer<String, String>>(){
            @Override
            protected KafkaConsumer<String, String> initialValue() {
                return new KafkaConsumer<String, String>(KafkaUtil.getKafkaConsumerProp(true));
            }
        };;
    	kafkaConsumer=tl.get();
    	this.topicList=topicList;
    	logger.info("KafkaUtil topicList:"+topicList);
		
		//kafkaConsumer.subscribe(this.topicList);
		kafkaConsumer.subscribe(topicList, new ConsumerRebalanceListener() {
            @Override
            public void onPartitionsRevoked(Collection<TopicPartition> collection) {

            }

            @Override
            public void onPartitionsAssigned(Collection<TopicPartition> collection) {
                Map<TopicPartition,Long> beginningOffset = kafkaConsumer.beginningOffsets(collection);

                //读取历史数据 --from-beginning
                for(Map.Entry<TopicPartition,Long> entry : beginningOffset.entrySet()){
                    // 基于seek方法
                    //TopicPartition tp = entry.getKey();
                    //long offset = entry.getValue();
                    //consumer.seek(tp,offset);

                    // 基于seekToBeginning方法
                	kafkaConsumer.seekToBeginning(collection);
                }
            }
        });
    }
    
    
    @RequestMapping("/insert")
    public void insert(){
    	cameraService.insert();
    }
    
    @RequestMapping("/findByMultipleEvent")
    public String findByMultipleEvent(Model model){
    	logger.info("/findByMultipleEvent。。。。");
    	//flag=true;
    	//if (flag) {
    		initTopic();
		//}
    	//if (topicList==null||kafkaConsumer==null) {
    	//	initTopic();
    	//}
    	getMessageFromKafka(model);
    	//flag=false;
    	count++;
    	logger.info("count:"+count);
    	return "pages/multipleEvent.jsp";
    	
    }
    
    @RequestMapping("/getMessage")
    @ResponseBody
    public Collection<Temp> getMessage(Model model){
    	logger.info("/getMessage。。。。");
    	
    	if (topicList==null||kafkaConsumer==null) {
    		logger.info("重新初始化topic");
    		initTopic();
    	}else {
    		if (topicList.size()==1) {
    			getMessageFromKafka(model);
    		}else {
    			topicList.clear();
    			topicList.add(topic);
    			getMessageFromKafka(model);
    		}
			
		}
    	/*for (int i = 0; i < 5; i++) {
			Temp t=new Temp();
			t.setAge(i);
			t.setName("25"+i);
			messageList.add(t);
		}*/
    	
    	
    	logger.info("newMessageList:"+newMessageList.size()+","+newMessageList);
    	/*logger.info("oldMessageList:"+oldMessageList.size()+","+oldMessageList);
    	
    	Collection<Temp> result;
		
    	if (newMessageList.size()>=oldMessageList.size()) {
			result=duplicateRemoval(newMessageList, oldMessageList);
			return result;
		}else {
//			result=duplicateRemoval(oldMessageList, newMessageList);
			return newMessageList;
		}*/
    	
		
    	return newMessageList;
//    	return "pages/multipleEvent.jsp";
    	
    }
    
    public Collection<Temp> duplicateRemoval(List<Temp> list1,List<Temp> list2){
    	Collection<Temp> result=CollectionUtils.subtract(list1,list2);
    	if (result.size()==0) {
			list1.clear();
			return list1;
//			return result;
		}else {
			list2.addAll(result);
			
			logger.info("result:"+result.size()+","+result);
			return result;
		}
    	
    }
    
    public void getMessageFromKafka(Model model){
    	newMessageList.clear();
    	Iterator<ConsumerRecord<String, String>> iterator=KafkaUtil.test(kafkaConsumer,topicList);
    	if (iterator!=null) {
			while(iterator.hasNext()){
				String vString=iterator.next().value();
				logger.info("getMessageFromKafka vString:"+vString);
				if (!(vString.contains("age"))) {
					continue;
				}
				String str=vString.replace("\\", "/");
//				logger.info("findByMultipleEvent str:"+str);
				Black black=new GsonBuilder()
						.setPrettyPrinting()
						.disableHtmlEscaping()
						.create().fromJson(str,Black.class);
				
				logger.info("getMessageFromKafka black:"+black);
				List<Camera> camerasList=cameraService.findById(black.getAlarm_device());
				if (camerasList!=null&&camerasList.size()>0) {
					
					Temp t=new Temp();
					if (!(black.getAge()==null||black.getAge().isEmpty()||black.getAge().equals("null"))) {
						
						t.setAge(Integer.parseInt(black.getAge()));
					}
					if (!(black.getName()==null||black.getName().isEmpty())) {
						
						t.setName(TestUnicode.decodeUnicode(TestUnicode.replace(black.getName())));
					}
					if (!(black.getGender()==null||black.getName().isEmpty())) {
						
						t.setGender(TestUnicode.decodeUnicode(TestUnicode.replace(black.getGender())));
					}
					if (camerasList!=null&&camerasList.size()>0) {
						
						t.setCamera_id(camerasList.get(0).getCamera_id());
						t.setLatitude(camerasList.get(0).getLatitude());
						t.setLongitude(camerasList.get(0).getLongitude());
					}
					t.setType(black.getAlarm_type());
					t.setPic_path(black.getAlarm_snapshot().replace("//192.168.1.130", "http://192.168.1.130:8080"));
					
					t.setTimestampStr(DateUtil.timeStamp2Date(black.getAlarm_time(),null));
					t.setStart_timeStr(DateUtil.timeStamp2Date(black.getStart_time(),null));
					t.setEnd_timeStr(DateUtil.timeStamp2Date(black.getEnd_time(),null));
					newMessageList.add(t);
				}
			}
		}
    	
    	newMessageList=newMessageList.stream()
    			.collect(Collectors.collectingAndThen(
    					Collectors.toCollection(() -> new TreeSet<Temp>(
    							Comparator.comparing(f -> ((Temp) f).getLatitude()+ ";" + ((Temp) f).getLongitude())))
    					, t -> new ArrayList<>(t)));
    	logger.info("newMessageList getMessageFromKafka:"+newMessageList.size()+","+newMessageList);
    	model.addAttribute("list", newMessageList);
    }
    
    @RequestMapping("/test-sendPoints")
    @ResponseBody
    public Map<String, String>  testSendPoints(@RequestBody  String points){
    	logger.info("/test-sendPoints。。。。"+points);
    	Gson gson=new Gson();
    	List<Point2> list= gson.fromJson(points, new TypeToken<List<Point2>>() {}.getType());
    	this.point2list=list;
        List<Camera> cameras=cameraService.findAll();
        List<Temp> filterList=new ArrayList<Temp>();
        for (int i = 0; i < cameras.size(); i++) {
			Camera camera=cameras.get(i);
			boolean b=Util.isPtInPoly2(camera.getLongitude().doubleValue(),camera.getLatitude().doubleValue(),list);
			System.out.println("lng:"+camera.getLongitude().doubleValue()+"lat:"+camera.getLatitude().doubleValue()+"b:"+b);
			if (b) {
				Temp t=new Temp();
				t.setLongitude(camera.getLongitude());
				t.setLatitude(camera.getLatitude());
				t.setCamera_id(camera.getCamera_id());
				filterList.add(t);
			}
		}
        
        this.filterList=filterList;
        
        Map<String, String> map2 = new HashMap<String, String>();
       
        String json=gson.toJson(filterList);
        System.out.println("json:"+json);
        map2.put("filterList",  json);
        return map2;
    	
    }
    
    @RequestMapping("/getCameraIds")
    @ResponseBody
    public Map<String, List<Temp>> getCameraIds(){
    	logger.info("/getCameraIds。。。。");
    	
    	
    	Map<String, List<Temp>> map = new HashMap<String, List<Temp>>();
        map.put("CameraIds", filterList);
        return map;
    	
    	
    }
    
    @RequestMapping("/getDangerLevel")
    @ResponseBody
    public Map<String, List<Danger_level>> getDangerLevel(@RequestParam String danger_type){
    	logger.info("/getDangerLevel。。。。");
    	List<Danger_level> list=danger_levelService.getDangerLevel(danger_type);
    	
    	Map<String, List<Danger_level>> map = new HashMap<String, List<Danger_level>>();
    	map.put("dangerLevel", list);
    	return map;
    	
    	
    }
    
    
    @RequestMapping("/getTarget_info")
    @ResponseBody
    public Map<String, List<Targets>> getTarget_info(@RequestParam int id){
    	logger.info("/getTarget_info。。。。");
    	List<Targets> list=target_infoService.getTarget_info(id);
    	
    	Map<String, List<Targets>> map = new HashMap<String, List<Targets>>();
    	map.put("target_info", list);
    	return map;
    	
    	
    }
    
    @RequestMapping("/getDanger_rank")
    @ResponseBody
    public Map<String, List<Danger_rank>> getDanger_rank(){
    	logger.info("/getDanger_rank。。。。");
    	List<Danger_rank> list=danger_rankService.getDanger_rank();
    	
    	Map<String, List<Danger_rank>> map = new HashMap<String, List<Danger_rank>>();
    	map.put("danger_rank", list);
    	return map;
    	
    	
    }
    
    @RequestMapping("/getGraph")
    @ResponseBody
    public Map<String, List<People>> getGraph(@RequestParam int id){
    	logger.info("/getGraph。。。。");
    	List<People> list=peopleService.getLink(id);
    	
    	Map<String, List<People>> map = new HashMap<String, List<People>>();
    	map.put("links", list);
    	return map;
    }
    
    
    @RequestMapping("/getPeople")
    @ResponseBody
    public Map<String, List<People>> getPeople(@RequestParam int id){
    	logger.info("/getPeople。。。。");
    	List<People> list=peopleService.getPeople(id);
    	
    	Map<String, List<People>> map = new HashMap<String, List<People>>();
    	map.put("people", list);
    	return map;
    }
    
    
    
}
