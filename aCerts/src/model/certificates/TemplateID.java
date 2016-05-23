package model.certificates;

/**
 * Created by dennis on 5/23/16.
 */
public class TemplateID
{
    private int templateID;

    private String templateName;

    public TemplateID(int templateID, String templateName)
    {
        this.templateID = templateID;
        this.templateName = templateName;
    }

    public int getTemplateID()
    {
        return templateID;
    }

    public void setTemplateID(int templateID)
    {
        this.templateID = templateID;
    }

    public String getTemplateName()
    {
        return templateName;
    }

    public void setTemplateName(String templateName)
    {
        this.templateName = templateName;
    }

    public String toString()
    {
        return templateName;
    }
}
