package org.wickedsource.docxstamper.util;

import org.docx4j.jaxb.Context;
import org.docx4j.wml.ContentAccessor;
import org.docx4j.wml.ObjectFactory;
import org.docx4j.wml.P;
import org.docx4j.wml.R;

public class ParagraphUtil {

    private static ObjectFactory objectFactory = Context.getWmlObjectFactory();

    private ParagraphUtil() {

    }

    /**
     * Creates a new paragraph.
     *
     * @param texts the text of this paragraph. If more than one text is specified each text will be placed within its own Run.
     * @return a new paragraph containing the given text.
     */
    public static P create(String... texts) {
        P p = objectFactory.createP();
        for (String text : texts) {
            R r = RunUtil.create(text, p);
            p.getContent().add(r);
        }
        return p;
    }

    /**
     * 查询当前段落在parent 中的位置
     *
     * @param paragraph
     * @return
     */
    public static int findParagraphCurrentIndexInParent(P paragraph) {
        if (paragraph == null) {
            throw new IllegalArgumentException("paragraph must not null");
        }
        int index = 0;
        if (paragraph.getParent() instanceof ContentAccessor) {
            for (Object p : ((ContentAccessor) paragraph.getParent()).getContent()) {
                if (p == paragraph) {
                    break;
                }
                index++;
            }
        }
        return index;
    }

    /**
     * 计算段落中 R的数量
     *
     * @param paragraph
     * @return
     */
    public static int calculateRunSize(P paragraph) {
        if (paragraph == null) {
            throw new IllegalArgumentException("paragraph must not null");
        }
        int index = 0;
        for (Object contentElement : paragraph.getContent()) {
            if (contentElement instanceof R && !"".equals(RunUtil.getText((R) contentElement))) {
                index++;
            }
        }
        return index;
    }

    /**
     * 删除当前段落
     *
     * @param paragraph
     */
    public static void deleteParagraph(P paragraph) {
        if (paragraph == null) {
            throw new IllegalArgumentException("paragraph must not null");
        }
        int pCurrentIndexInParent = ParagraphUtil.findParagraphCurrentIndexInParent(paragraph);
        ((ContentAccessor) paragraph.getParent()).getContent().remove(pCurrentIndexInParent);
    }

    /**
     * 获取段落的文本信息
     *
     * @param paragraph
     * @return
     */
    public static String getParagraphText(P paragraph) {
        if (paragraph == null) {
            throw new IllegalArgumentException("paragraph must not null");
        }
        StringBuilder builder = new StringBuilder();
        for (Object contentElement : paragraph.getContent()) {
            if (contentElement instanceof R) {
                builder.append(RunUtil.getText((R) contentElement));
            }
        }
        return builder.toString();
    }
}
