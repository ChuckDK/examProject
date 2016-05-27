package model.certificates;

//Class from which enables initializing of TemplateID objects. The class holds some getters and setters which
//may or may not be in use. The ones not in use are for later convenience.
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
