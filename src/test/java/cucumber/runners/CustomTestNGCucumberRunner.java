package cucumber.runners;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//


import cucumber.api.testng.CucumberExceptionWrapper;
import cucumber.api.testng.CucumberFeatureWrapperImpl;
import cucumber.api.testng.FeatureResultListener;
import cucumber.api.testng.TestNgReporter;
import cucumber.runtime.ClassFinder;
import cucumber.runtime.CucumberException;
import cucumber.runtime.Runtime;
import cucumber.runtime.RuntimeOptions;
import cucumber.runtime.RuntimeOptionsFactory;
import cucumber.runtime.io.MultiLoader;
import cucumber.runtime.io.ResourceLoader;
import cucumber.runtime.io.ResourceLoaderClassFinder;
import cucumber.runtime.model.CucumberFeature;
import gherkin.formatter.Formatter;
import gherkin.formatter.model.Tag;

import java.util.*;

public class CustomTestNGCucumberRunner {
    private Runtime runtime;
    private RuntimeOptions runtimeOptions;
    private ResourceLoader resourceLoader;
    private FeatureResultListener resultListener;
    private ClassLoader classLoader;

    public CustomTestNGCucumberRunner(Class clazz) {
        this.classLoader = clazz.getClassLoader();
        this.resourceLoader = new MultiLoader(this.classLoader);
        RuntimeOptionsFactory runtimeOptionsFactory = new RuntimeOptionsFactory(clazz);
        this.runtimeOptions = runtimeOptionsFactory.create();
        new TestNgReporter(System.out);
        ClassFinder classFinder = new ResourceLoaderClassFinder(this.resourceLoader, this.classLoader);
        this.resultListener = new FeatureResultListener(this.runtimeOptions.reporter(this.classLoader), this.runtimeOptions.isStrict());
        this.runtime = new Runtime(this.resourceLoader, classFinder, this.classLoader, this.runtimeOptions);
    }

    public void runCukes() {
        Iterator var1 = this.getFeatures().iterator();

        while(var1.hasNext()) {
            CucumberFeature cucumberFeature = (CucumberFeature)var1.next();
            cucumberFeature.run(this.runtimeOptions.formatter(this.classLoader), this.resultListener, this.runtime);
        }

        this.finish();
        if (!this.resultListener.isPassed()) {
            throw new CucumberException(this.resultListener.getFirstError());
        }
    }

    public void runCucumber(CucumberFeature cucumberFeature) {
        this.resultListener.startFeature();
        cucumberFeature.run(this.runtimeOptions.formatter(this.classLoader), this.resultListener, this.runtime);
        if (!this.resultListener.isPassed()) {
            throw new CucumberException(this.resultListener.getFirstError());
        }
    }

    public void finish() {
        Formatter formatter = this.runtimeOptions.formatter(this.classLoader);
        formatter.done();
        formatter.close();
        this.runtime.printSummary();
    }

    public List<CucumberFeature> getFeatures() {
        List<CucumberFeature> cucumberFeaturesList = this.runtimeOptions.cucumberFeatures(this.resourceLoader);
        List<CucumberFeature> cucumberFeaturesFilteredListByFeatureName =
                this.getFilteredFeatureListByFeature(cucumberFeaturesList);
        return this.getFilteredFeatureListByFeatureByTag(cucumberFeaturesFilteredListByFeatureName);
    }

    private List<CucumberFeature> getFilteredFeatureListByFeatureByTag(List<CucumberFeature> cucumberFeaturesList) {
        String runtimeCucumberFeaturesTags = System.getProperty("TagName");
        if(runtimeCucumberFeaturesTags == null) {
            return cucumberFeaturesList;
        }
        String[] runtimeCucumberFeaturesTagsNames = runtimeCucumberFeaturesTags.split(",");
        Map<String, CucumberFeature> featureMap = this.getFeatureMapping(cucumberFeaturesList, "TAGS");
        return this.filterFeaturesFromMapping(featureMap, "TAGS", runtimeCucumberFeaturesTagsNames);
    }

    private List<CucumberFeature> getFilteredFeatureListByFeature(List<CucumberFeature> cucumberFeaturesList) {
        String runtimeCucumberFeatures = System.getProperty("FeatureName");
        if(runtimeCucumberFeatures == null) {
            return cucumberFeaturesList;
        }
        String[] runtimeCucumberFeaturesNames = runtimeCucumberFeatures.split(",");
        Map<String, CucumberFeature> featureMap = this.getFeatureMapping(cucumberFeaturesList, "FEATURES");
        return this.filterFeaturesFromMapping(featureMap, "FEATURES", runtimeCucumberFeaturesNames);
    }

    private List<CucumberFeature> filterFeaturesFromMapping(Map<String, CucumberFeature> cucumberFeaturesMap,
                                                            String filterName, String[] keyNames) {
        List<CucumberFeature> cucumberFeaturesFilteredList = new ArrayList<>();;
        int length;
        if(filterName.equals("TAGS")) {
            length = cucumberFeaturesMap.size();
        } else {
            length = keyNames.length;
        }
        for(int i = 0; i < length; i++) {
            if(filterName.equals("FEATURES")) {
                cucumberFeaturesFilteredList.add(cucumberFeaturesMap.get(keyNames[i]));
            } else if(filterName.equals("TAGS")) {
                CucumberFeature cucumberFeature = cucumberFeaturesMap.get(
                        cucumberFeaturesMap.keySet().toArray()[i]);
                List<Tag> tagList = cucumberFeature.getGherkinFeature().getTags();
                for(Tag tag: tagList) {
                    if(Arrays.asList(keyNames).contains(tag.getName())) {
                        cucumberFeaturesFilteredList.add(cucumberFeature);
                    }
                }
            }
        }
        return cucumberFeaturesFilteredList;
    }

    private Map<String, CucumberFeature> getFeatureMapping(List<CucumberFeature> cucumberFeaturesList,
                                                           String filterName) {
        Map<String, CucumberFeature> cucumberFeaturesMap = new HashMap<>();

        for(CucumberFeature cucumberFeature : cucumberFeaturesList) {
            cucumberFeaturesMap.put(cucumberFeature.getGherkinFeature().getName(), cucumberFeature);
        }
        return cucumberFeaturesMap;
    }

    public Object[][] provideFeatures() {
        try {
            List<CucumberFeature> features = this.getFeatures();
            List<Object[]> featuresList = new ArrayList(features.size());
            Iterator var3 = features.iterator();

            while(var3.hasNext()) {
                CucumberFeature feature = (CucumberFeature)var3.next();
                featuresList.add(new Object[]{new CucumberFeatureWrapperImpl(feature)});
            }

            return (Object[][])featuresList.toArray(new Object[0][]);
        } catch (CucumberException var5) {
            return new Object[][]{{new CucumberExceptionWrapper(var5)}};
        }
    }
}

