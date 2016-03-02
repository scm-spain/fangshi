package com.scmspain.bigdata.hadoop.cli;

import java.util.ArrayList;

public class Options
{
    private ArrayList<OptionDefinition> options = new ArrayList<OptionDefinition>();

    public Integer getOptionsSize()
    {
        return options.size();
    }

    public Options addOption(String longOption, String shortOption, String help) throws InvalidArgumentException
    {
        options.add(new OptionDefinition(longOption, shortOption, help));

        return this;
    }

    public OptionDefinition getOption(String key) throws OptionNotFoundException
    {
        for (OptionDefinition optionDefinition : options) {
            if (optionDefinition.getLongOption().equals(key) || optionDefinition.getShortOption().equals(key)) {
                return optionDefinition;
            }
        }

        throw new OptionNotFoundException("Argument " + key + " not found");
    }
}
