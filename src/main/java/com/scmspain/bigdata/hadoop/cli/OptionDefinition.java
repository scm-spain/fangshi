package com.scmspain.bigdata.hadoop.cli;

class OptionDefinition
{
    private String longOption;
    private String shortOption;
    private String help;
    private String value = "";
    private Boolean isPresent = false;

    public OptionDefinition(String longOption, String shortOption, String help) throws InvalidArgumentException
    {
        checkShortOption(shortOption);

        this.longOption = longOption;
        this.shortOption = shortOption;
        this.help = help;
    }

    private boolean checkShortOption(String shortOption) throws InvalidArgumentException
    {
        if (1 < shortOption.length()) {
            throw new InvalidArgumentException("Short option should be one char length");
        }

        return true;
    }

    public String getLongOption()
    {
        return longOption;
    }

    public String getShortOption()
    {
        return shortOption;
    }

    public String getHelp()
    {
        return help;
    }

    public Boolean hasValue()
    {
        return isPresent;
    }

    public String getValue()
    {
        return (isPresent) ? value : null;
    }

    protected OptionDefinition setValue(String value)
    {
        this.value = value;
        isPresent = true;

        return this;
    }
}
