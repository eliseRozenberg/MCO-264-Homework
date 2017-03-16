package blobsRecursive;

public interface SetTable<T> {
  public void reset();
  
  public void setVisited();
  
  public void setData(T data);
  
  public void resetData();
  
  public void resetVisited();
  
  public int getRow();
  
  public int getCol();
}
