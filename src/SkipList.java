public interface SkipList {

    public MySkipList.MySkipListEntry findEntry(String key);
    public Integer get(String key);

    public Integer put(String key, Integer value);

    public Integer remove(String key);
    
    public Integer getValueOfMaxKey();

    public Integer getValueOfMaxLevel();

}
