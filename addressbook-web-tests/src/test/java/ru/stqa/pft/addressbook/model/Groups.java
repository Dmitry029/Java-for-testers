package ru.stqa.pft.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Администратор on 14.02.2017.
 */
public class Groups extends ForwardingSet<GroupData> {

  private Set<GroupData> delegate;

    // Конструктор
  public Groups(Groups groups) {
    this.delegate = new HashSet<GroupData>(groups.delegate);
  }

  // Это конструктор с 0 значением
  public Groups() {
    this.delegate = new HashSet<GroupData>();
  }

  @Override
  protected Set<GroupData> delegate() {
    return delegate;
  }

  //Копия куда (в groups) добавили группу
  public Groups withAdded(GroupData group){
    Groups groups = new Groups(this);//создаем копию существующего объекта
    groups.add(group); //добавляем новую группу
    return groups; //возврат копии с добавленной группой
  }

  //Копия из которой удалили группу
  public Groups without(GroupData group){
    Groups groups = new Groups(this);
    groups.remove(group); //удаляем группу
    return groups;
  }
}
