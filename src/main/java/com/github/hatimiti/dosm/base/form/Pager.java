package com.github.hatimiti.dosm.base.form;

import com.github.hatimiti.dosm.base.Form;
import org.apache.catalina.util.URLEncoder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.util.ReflectionUtils;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

public class Pager<T> implements Page<T> {

    private final Page page;

    /** ナビゲーションのページリンク候補一覧 */
    private List<Integer> pageNumberList;
    /** 検索条件 */
    private String searchConditions;

    public Pager(
            final Page page,
            final int pageRangeSize,
            final Form form) {
        this.page = page;
        this.pageNumberList = createPageNumberList(pageRangeSize);
        this.searchConditions = createSearchConditions(form);
    }

    private String createSearchConditions(
            final Form form) {

        final StringBuilder sb = new StringBuilder();

        ReflectionUtils.doWithLocalFields(form.getClass(), formField -> {
            formField.setAccessible(true);
            PageParam param = formField.getAnnotation(PageParam.class);
            if (param == null) {
                return;
            }
            final String name = formField.getName();
            if ("pageNumber".equals(name)) {
                return;
            }
            try {
                Object value = formField.get(form);
                if (value != null) {
                    if (param.escape()) {
                        value = new URLEncoder()
                                .encode(value.toString(), StandardCharsets.UTF_8);
                    }
                    sb.append("&").append(name + "=" + value);
                }
            } catch (Exception e) {
                throw new IllegalStateException(e);
            }
        });

        for (Order order : getPageable().getSort()) {
            sb.append("&")
                    .append("sort=" + order.getProperty())
                    .append(",").append(order.getDirection().name());
        }

        return sb.toString();
    }

    /**
     * totalPages = 10
     * pageRangeSize = 5
     * (1) 2 3 4 5
     * 1 (2) 3 4 5
     * 1 2 (3) 4 5
     * 2 3 (4) 5 6
     * 3 4 (5) 6 7
     * 4 5 (6) 7 8
     * 5 6 (7) 8 9
     * 6 7 (8) 9 10
     * 6 7 8 (9) 10
     * 6 7 8 9 (10)
     * @param pageRangeSize
     * @return
     */
    private List<Integer> createPageNumberList(final int pageRangeSize) {
        final List<Integer> list = new ArrayList<>();
        final int nowPageNumber = getNumber() + 1;
        final int halfRangeSize = pageRangeSize / 2;

        int start = nowPageNumber - halfRangeSize;
        int end = nowPageNumber + halfRangeSize;
        int surplus = pageRangeSize % 2;
        for (int i = start; i < end; i++) {
            if (i <= 0) {
                start++;
                end++;
                end += surplus;
                surplus = 0;
                continue;
            }
            if (getTotalPages() < i) {
                end--;
                start--;
                start -= surplus;
                surplus = 0;
                continue;
            }
        }
        if (end + surplus <= getTotalPages() + 1) {
            end += surplus;
        } else {
            start -= surplus;
        }

        for (int i = start; i < end; i++) {
            if (i <= 0 || getTotalPages() < i) {
                continue;
            }
            list.add(i);
        }

        return list;
    }

    public List<Integer> getPageNumberList() {
        return Collections.unmodifiableList(this.pageNumberList);
    }

    public String getSearchConditions() {
        return searchConditions;
    }

    @Override
    public int getTotalPages() {
        return page.getTotalPages();
    }

    @Override
    public long getTotalElements() {
        return page.getTotalElements();
    }

    @Override
    public int getNumber() {
        return page.getNumber();
    }

    @Override
    public int getSize() {
        return page.getSize();
    }

    @Override
    public int getNumberOfElements() {
        return page.getNumberOfElements();
    }

    @Override
    public List<T> getContent() {
        return page.getContent();
    }

    @Override
    public boolean hasContent() {
        return page.hasContent();
    }

    @Override
    public Sort getSort() {
        return page.getSort();
    }

    @Override
    public boolean isFirst() {
        return page.isFirst();
    }

    @Override
    public boolean isLast() {
        return page.isLast();
    }

    @Override
    public boolean hasNext() {
        return page.hasNext();
    }

    @Override
    public boolean hasPrevious() {
        return page.hasPrevious();
    }

    @Override
    public Pageable nextPageable() {
        return page.nextPageable();
    }

    @Override
    public Pageable previousPageable() {
        return page.previousPageable();
    }

    @Override
    public <U> Page<U> map(Function<? super T, ? extends U> converter) {
        return page.map(converter);
    }

    @Override
    public Iterator<T> iterator() {
        return page.iterator();
    }
}
