package ru.stqa.pft.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Администратор on 14.02.2017.
 */
public class Groups extends ForwardingSet<GroupData> {

  private Set<GroupData> delegate;

  public Groups(Groups groups) {
    this.delegate = new HashSet<GroupData>(groups.delegate);
  }

  public Groups() {
    this.delegate = new HashSet<GroupData>();
  }

  @Override
  protected Set<GroupData> delegate() {
    return delegate;
  }

  //Копия куда добавили группу
  public Groups withAdded(GroupData group){
    Groups groups = new Groups(this);
    groups.add(group); //добавляем новую группу
    return groups;
  }

  //Копия из которой удалили группу
  public Groups without(GroupData group){
    Groups groups = new Groups(this);
    groups.remove(group); //удаляем группу
    return groups;
  }
}
