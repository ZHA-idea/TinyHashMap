package space.glimmer.lab.container;

import space.glimmer.lab.GlimmerHashMap;

/**
 * @author Lehr
 * @create: 2021-01-16
 * 链表的实现,需要完成除了getType的其他接口
 * 你可以选择自己手写一遍实现,也可以学习使用Java 泛型容器里的LinkedList实现好了的链表直接写这里
 */
public class NodeList implements BucketContainer{

    public Entry[] retEntrys;
    public int threshold = 0;
    class Node {
        public Entry E = null;
        public Node next = null;
    }
    private Node head;

    /**
     初始化操作，获取头节点
     */
    public NodeList(){
        head = new Node();
        head.next = null;
    }


    /**
     * 写死的,不能修改,用来判断具体的数据结构
     * @return
     */
    @Override
    public String getType() {
        return "nodelist";
    }

    /**
     * 搜索某个元素并返回
     * 其中key是这个元素Entry的key
     * @param key
     * @return
     */
    @Override
    public Entry searchElement(String key) {
        //todo:write your code here for part-a
        Node p = head.next;
        while(p != null){
            if(p.E == null){
               System.out.println("这个节点没有Entry??我迷了???");
            }else if(key.equals(p.E.key)){
                System.out.println("nl通过k:"+key+"找到了v:"+p.E.value);
                return p.E;
            }
            p = p.next;
        }
        System.out.println("nl没有找到k:"+key+"对应的v");
        return null;
    }

    /**
     * 添加一个元素
     * @param e
     */
    @Override
    public void addElement(Entry e) {
        //todo:write your code here for part-a
        Node p = head;
        while(p.next != null){
            p = p.next;
        }
        p.next = new Node();
        p.next.E = e;
        System.out.println("节点k:"+e.key+",v:"+e.value+"添加成功");
        threshold++;
    }


    /**
     * 更新一个节点
     * @param e
     */
    @Override
    public void updateElement(Entry e) {
        //todo:write your code here for part-a
        Node p = head;
        while(p.next != null){
            p = p.next;
            if(p.E == null){
                System.out.println("这个节点没有Entry");
            }else if(p.E.key == e.key){
                p.E = e;
                System.out.println("节点k:"+e.key+",v:"+e.value+"更新成功");
            }
        }
    }

    /**
     * 删除一个节点
     * @param key
     */
    @Override
    public void removeElement(String key) {
        //todo:write your code here for part-a
        Node p = head;
        while(p.next != null){
            if(p.next.E.key == key){

                System.out.println("k:"+key+",v:"+p.next.E.value+"删除成功！");
                threshold--;
                p.next = p.next.next;
                return;
            }else {
                p = p.next;
            }
        }
        if(p.next == null){
            System.out.println("删除失败：没有这个节点");
        }
    }

    /**
     * 遍历并返回所有元素
     * @return
     */
    @Override
    public Entry[] traverse() {
        //todo:write your code here for part-a
        Node p = head.next;
        retEntrys = new Entry[threshold];
        int i = 0;
        while(p != null){
            retEntrys[i] = p.E;
            i++;
            p = p.next;
        }
        return retEntrys;
    }
}
