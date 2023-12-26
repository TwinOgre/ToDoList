package com.example.demo.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
public class HomeController {
    int a = -1;
    int lastId = 1;
    List<Person> personList = new ArrayList<>();
    Map<Person, Objects> personObjectsMap = new HashMap<>();

    @GetMapping("/hello")
    @ResponseBody
    public String index (){
        return "안녕하세요";
    }
    @GetMapping("/name")
    @ResponseBody
    public String name (){
        return "저는 000 입니다.";
    }
    @GetMapping("/age")
    @ResponseBody
    public String age (){
        return "저는 00 살 입니다.";
    }
    @GetMapping("/home/increase")
    @ResponseBody
    public int home (){
        a++;
        return a;
    }

    @GetMapping("/home/person")
    @ResponseBody
    public String getperson() {

        return "";
    }

    @GetMapping("/home/addPerson")
    @ResponseBody
    public String addPerson(@RequestParam("name")String name,@RequestParam("age") int age){
        Person person = new Person(lastId,name,age);
        personList.add(person);

        lastId++;
        return (person.getId() + "번 사람이 추가되었습니다.");
    }
    @GetMapping("/home/removePerson")
    @ResponseBody
    public String removePerson(@RequestParam("id")int id){
        try{
            for(int i = 0; i < personList.size(); i++){
                if(personList.get(i).getId() == id){
                    personList.remove(i);
                    return (id+"번 사람이 삭제되었습니다.");
                }
            }
            return (id+"번 사람이 존재하지 않습니다.");
        }catch (IndexOutOfBoundsException e){
            return (id + "번 사람이 존재하지 않습니다..");
        }
    }

    @GetMapping("/home/modifyPerson")
    @ResponseBody
    public String modifyPerson(@RequestParam("id") int id,@RequestParam("name")String name,@RequestParam("age") int age){
        try{
            for(int i = 0; i < personList.size(); i++){
                if(personList.get(i).getId() == id){
                    personList.get(i).setName(name);
                    personList.get(i).setAge(age);
                    return (id+"번 사람이 수정되었습니다.");
                }
            }
            return (id+"번 사람이 존재하지 않습니다.");
        }catch (IndexOutOfBoundsException e){
            return (id + "번 사람이 존재하지 않습니다..");
        }
    }

    @GetMapping("/home/people")
    @ResponseBody
    public List<Person> homePeople(){

        return personList;
    }
}
