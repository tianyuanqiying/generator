package com.chinaclear.sz.component.generator;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GeneratorFactory {
    private static List<Generator> generators = new ArrayList<>();

    static {
        //添加查询目录生成器
        generators.add(new QueryGenerator());
        //添加参维目录生成器
        generators.add(new ParamGenerator());
        //添加流程目录生成器
        generators.add(new ProcessGenerator());
        //添加组件目录生成器
        generators.add(new ComponentGenerator());

        generators.add(new BuildGradleGenerator());
    }

    public static List<Generator> getGenerators() {
        return generators;
    }

    public static List<Generator> getByType(String type) {
        List<Generator> generatorList = getGenerators();
        List<Generator> generators = new ArrayList<>();
        for (Generator generator : generatorList) {
            if (generator.isSupport(type)) {
                generators.add(generator);
            }
        }
        return generators;
    }
}
