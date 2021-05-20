package com.celeste.library.spigot.model.paginator.impl;

import com.celeste.library.spigot.model.paginator.Paginator;
import com.celeste.library.spigot.model.paginator.AbstractPaginator;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public class PaginatorImpl<T> extends AbstractPaginator<T> {

  private int currentPage;

  public PaginatorImpl(final int[] shape, final List<T> source) {
    super(shape, source);
    this.currentPage = 1;
  }

  /**
   * Returns the number of pages that his paginator contains depending on his shape and source
   * size.
   *
   * @return int
   */
  @Override
  public int totalPages() {
    return (int) Math.ceil((double) getSource().size() / getShape().length);
  }

  /**
   * Checks if it contains that page.
   *
   * @param page int
   * @return boolean If exists
   */
  @Override
  public boolean hasPage(final int page) {
    return page >= 0 && page < totalPages();
  }

  @Override
  public boolean isFirst() {
    return currentPage == 0;
  }

  @Override
  public boolean isLast() {
    return currentPage == totalPages();
  }

  @Override
  public Paginator<T> first() {
    this.currentPage = 0;
    return this;
  }

  @Override
  public Paginator<T> page(final int page) {
    this.currentPage = page;
    return this;
  }

  @Override
  public Paginator<T> previous() {
    this.currentPage = currentPage - 1;
    return this;
  }

  @Override
  public Paginator<T> next() {
    this.currentPage = currentPage + 1;
    return this;
  }

  /**
   * Returns the resource on that index
   *
   * @param index int
   * @return T
   */
  @Override
  public T getItem(final int index) {
    return getSource().get(index);
  }

  /**
   * Gets all items registered on that page in the Paginator
   *
   * @param page Integer
   * @return List
   */
  @Override
  public List<T> getItems(final int page) {
    final int sourceSize = getSource().size();
    final int shapeLength = getShape().length;

    final List<T> items = new ArrayList<>();

    if (sourceSize == 0) {
      return items;
    }

    if (sourceSize < shapeLength) {
      final List<T> source = getSource();
      return new ArrayList<>(source);
    }

    if (page < 0 || page >= totalPages()) {
      throw new ArrayIndexOutOfBoundsException("The page must be more than 1 to a maximum of "
          + totalPages() + ", given: " + page);
    }

    for (int index = shapeLength * page; index < shapeLength; index++) {
      final T item = getItem(index);
      items.add(item);
    }

    return items;
  }

}