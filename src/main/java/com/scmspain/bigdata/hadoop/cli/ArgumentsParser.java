package com.scmspain.bigdata.hadoop.cli;

import java.util.regex.Pattern;

public class ArgumentsParser
{
    private Options options;

    public ArgumentsParser(Options options)
    {
        this.options = options;
    }

    public ArgumentsParser parse(String[] args) throws OptionNotFoundException
    {
        OptionDefinition optionDefinition = null;
        Pattern regex = Pattern.compile("^-{1,2}([a-z-]*)", Pattern.CASE_INSENSITIVE);
        for (String arg : args) {
            if (!arg.matches(regex.toString()) && optionDefinition != null) {
                optionDefinition.setValue(
                        ((optionDefinition.hasValue()) ? optionDefinition.getValue() : "") + arg
                );
            } else {
                optionDefinition = options.getOption(arg.replaceAll("^-{1,2}", "")).setValue("");
            }
        }

        return this;
    }

    public Boolean hasOption(String key) throws OptionNotFoundException
    {
        return options.getOption(key).hasValue();
    }

    public String getOptionValue(String key) throws OptionNotFoundException
    {
        return options.getOption(key).getValue();
    }
}
