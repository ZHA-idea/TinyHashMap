package space.glimmer.lab.container;

import space.glimmer.lab.GlimmerHashMap;

/**
 * @author Lehr
 * @create: 2021-01-16
 * 二叉搜索树实现,需要完成除了getType的其他接口
 */
public class Bst implements BucketContainer{

    public int threshold = 1;
    public Entry[] retEntrys;
    class Node{
        Entry E;
        Node left;
        Node right;
    }

    public Node root;
    int i = 0;
    /**
     * 写死的,不能修改
     * @return
     */
    @Override
    public String getType() {
        return "bst";
    }


    /**
     * 搜索某个元素并返回
     * 其中key是这个元素Entry的key
     * @param key
     * @return
     */
    @Override
    public Entry searchElement(String key) {
        //todo:write your code here for part-b
        if(root == null){
            System.out.println("没有找到k:"+key+"对应的Entry，因为没有root");
            return null;
        }else{
            Node p = root;
            while(true){
                if(key.compareTo(p.E.key) < 0){
                    if(p.left != null){ p = p.left;}
                    else{
                        System.out.println("bst查找节点失败：没有这个节点");
                        return  null;
                    }
                }else if(key.compareTo(p.E.key) > 0){
                    if(p.right != null){ p = p.right;}
                    else{
                        System.out.println("bst查找节点失败：没有这个节点");
                        return  null;
                    }
                }else if(key.compareTo(p.E.key) == 0){
//                    p.E.value = e.value;
                    System.out.println("bst找到了节点k:"+key+",v:"+p.E.value+"");
                    return p.E;
                }
            }
        }
    }

    /**
     * 添加一个元素
     * @param e
     */
    @Override
    public void addElement(Entry e) {
        //todo:write your code here for part-b
        if(root == null){
            root = new Node();
            root.E = e;
            System.out.println("根节点k:"+ e.key+",v:"+ e.value+"已添加");
            threshold++;
        }
        else if(root != null){

            Node p = root;
            while(true){
                if(e.key.compareTo(p.E.key) < 0){
                    if(p.left != null){ p = p.left;}
                    else{
                        p.left = new Node();
                        p.left.E = e;
                        System.out.println("节点k:"+ e.key+",v:"+ e.value+"已添加");
                        threshold++;
                        break;
                    }
                }else if(e.key.compareTo(p.E.key) > 0){
                    if(p.right != null){ p = p.right;}
                    else{
                        p.right = new Node();
                        p.right.E = e;
                        System.out.println("节点k:"+ e.key+",v:"+ e.value+"已添加");
                        threshold++;
                        break;
                    }
                }
            }
        }
    }

    /**
     * 更新一个节点
     * @param e
     */
    @Override
    public void updateElement(Entry e) {
        //todo:write your code here for part-b
        if(root != null){
            Node p = root;
            while(true){
                if(e.key.compareTo(p.E.key) < 0){
                    if(p.left != null){ p = p.left;}
                    else{
                        System.out.println("更新节点失败：没有这个节点");
                        break;
                    }
                }else if(e.key.compareTo(p.E.key) > 0){
                    if(p.right != null){ p = p.right;}
                    else{
                        System.out.println("更新节点失败：没有这个节点");
                        break;
                    }
                }else if(e.key.compareTo(p.E.key) == 0){
                    p.E.value = e.value;
                    System.out.println("节点k:"+ e.key+",v:"+ e.value+"已更新");
                }
            }
        }
    }

    /**
     * 删除一个节点
     * @param key
     */
    @Override
    public void removeElement(String key) {
        //todo:write your code here for part-b
        if(root != null){
            Node p = root;
            Node q = root;
            while(true){
                if(key.compareTo(p.E.key) < 0){
                    if(p.left != null){
                        p = p.left;
                        System.out.println("p左移");
                    }
                    else{
                        System.out.println("删除节点失败：没有这个节点");
                        break;
                    }
                }else if(key.compareTo(p.E.key) > 0){
                    if(p.right != null){
                        p = p.right;
//                        System.out.println("p右移");
                    }
                    else{
                        System.out.println("删除节点失败：没有这个节点");
                        break;
                    }
                }else if(key.compareTo(p.E.key) == 0) {
//                    System.out.println("删除：p就绪");
                    break;
                }
            }
            if(p == root){
                System.out.println("将删除root");
                if(p.left == null && p.right == null){ root = null;}
                else if(p.left != null && p.right == null){root = p.left;p = null;}
                else if(p.left == null && p.right != null){root = p.right; p = null; }
                else if(p.left != null && p.right != null){
                    root = p.left;
                    q = p.left;
                    while(q.right != null){
                        q = q.right;
                    }
                    q.right = p.right;
                    p = null;
                }
                System.out.println("删除k:"+key+"成功");
                threshold--;
            }else {
                while (q != null) {
                    if(q.left == p || q.right == p){
                        break;
                    }
                    if (key.compareTo(q.E.key) < 0) {
                        q = q.left;
                        System.out.println(q);
                    } else if (key.compareTo(q.E.key) > 0) {
                        q = q.right;
                        System.out.println(q);
                    }

                }
                if (q.left == p) {
                    //System.out.println("删除：q就绪");
                    if (p.left == null && p.right == null) {
                        p = null;
                    } else if (p.left == null && p.right != null) {
                        q.left = p.right;
                    } else if (p.left != null && p.right == null) {
                        q.left = p.left;
                    } else if (p.left != null && p.right != null) {
                        Node t = p.left;
                        q.left = p.left;
                        while (t.right != null) {
                            t = t.right;
                        }
                        t.right = p.right;
                        p = null;
                    }

                }
                if (q.right == p) {
                    //System.out.println("删除：q就绪");
                    if (p.left == null && p.right == null) {
                        p = null;
                    } else if (p.left == null && p.right != null) {
                        q.right = p.right;
                    } else if (p.left != null && p.right == null) {
                        q.right = p.left;
                    } else if (p.left != null && p.right != null) {
                        Node t = p.right;
                        q.right = p.right;
                        while (t.left != null) {
                            t = t.left;
                        }
                        t.left = p.left;
                        p = null;
                    }

                }
                System.out.println("删除k:"+key+"成功");
                threshold--;
            }
//                    if(p.left == null && p.right == null) {
//                        p = null;
//                    } else if (p.left == null && p.right != null) {
//
//
//                    }
//                    System.out.println("节点k:"+ key+",v:"+ p.E.value+"已删除");


        }else if(root == null){
            System.out.println("删除失败，没有root");
            return;
        }
    }

    /**
     * 按照树的"先序遍历",遍历并返回所有元素
     * @return
     */
    @Override
    public Entry[] traverse() {
        //todo:write your code here for part-b
        retEntrys = new Entry[threshold];
        if(root == null){return null;}
        retEntry(root);

        return retEntrys;
    }

    //todo:compare
    private Entry compare(Node node, String key){
        if(node.left != null){
            if(compare(node.left, key) != null){
                return compare(node.left,key);
            }
        }
        if (node.right != null){
            if(compare(node.right,key) != null){
                return compare(node.right, key);
            }
        }
        if(node.E.key == key){
            return node.E;
        }else{
            return null;
        }
        //return null;
    }
    private void retEntry(Node node){
        retEntrys[i] = node.E;
        i++;
        if(node.left != null){
            retEntry(node.left);
        }
        if(node.right != null){
            retEntry(node.right);
        }
    }
}
