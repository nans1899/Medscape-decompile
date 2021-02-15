package com.google.firebase.icing;

public final class R {

    public static final class attr {
        public static final int allowShortcuts = 2130968619;
        public static final int contentProviderUri = 2130968821;
        public static final int corpusId = 2130968837;
        public static final int corpusVersion = 2130968838;
        public static final int defaultIntentAction = 2130968903;
        public static final int defaultIntentActivity = 2130968904;
        public static final int defaultIntentData = 2130968905;
        public static final int documentMaxAgeSecs = 2130968922;
        public static final int featureType = 2130969005;
        public static final int indexPrefixes = 2130969081;
        public static final int inputEnabled = 2130969083;
        public static final int noIndex = 2130969303;
        public static final int paramName = 2130969328;
        public static final int paramValue = 2130969329;
        public static final int perAccountTemplate = 2130969337;
        public static final int schemaOrgProperty = 2130969405;
        public static final int schemaOrgType = 2130969406;
        public static final int searchEnabled = 2130969411;
        public static final int searchLabel = 2130969414;
        public static final int sectionContent = 2130969418;
        public static final int sectionFormat = 2130969419;
        public static final int sectionId = 2130969420;
        public static final int sectionType = 2130969421;
        public static final int sectionWeight = 2130969422;
        public static final int semanticallySearchable = 2130969428;
        public static final int settingsDescription = 2130969429;
        public static final int sourceClass = 2130969479;
        public static final int subsectionSeparator = 2130969510;
        public static final int toAddressesSection = 2130969613;
        public static final int trimmable = 2130969640;
        public static final int userInputSection = 2130969655;
        public static final int userInputTag = 2130969656;
        public static final int userInputValue = 2130969657;

        private attr() {
        }
    }

    public static final class id {
        public static final int contact = 2131362468;
        public static final int date = 2131362558;
        public static final int demote_common_words = 2131362585;
        public static final int demote_rfc822_hostnames = 2131362586;
        public static final int email = 2131362708;
        public static final int html = 2131362912;
        public static final int icon_uri = 2131362923;
        public static final int index_entity_types = 2131362953;
        public static final int instant_message = 2131362967;
        public static final int intent_action = 2131362970;
        public static final int intent_activity = 2131362971;
        public static final int intent_data = 2131362972;
        public static final int intent_data_id = 2131362973;
        public static final int intent_extra_data = 2131362974;
        public static final int large_icon_uri = 2131363012;
        public static final int match_global_nicknames = 2131363116;
        public static final int omnibox_title_section = 2131363280;
        public static final int omnibox_url_section = 2131363281;
        public static final int plain = 2131363333;
        public static final int rfc822 = 2131363489;
        public static final int text1 = 2131363728;
        public static final int text2 = 2131363729;
        public static final int thing_proto = 2131363796;
        public static final int url = 2131363881;

        private id() {
        }
    }

    public static final class styleable {
        public static final int[] AppDataSearch = new int[0];
        public static final int[] Corpus = {com.medscape.android.R.attr.contentProviderUri, com.medscape.android.R.attr.corpusId, com.medscape.android.R.attr.corpusVersion, com.medscape.android.R.attr.documentMaxAgeSecs, com.medscape.android.R.attr.perAccountTemplate, com.medscape.android.R.attr.schemaOrgType, com.medscape.android.R.attr.semanticallySearchable, com.medscape.android.R.attr.trimmable};
        public static final int Corpus_contentProviderUri = 0;
        public static final int Corpus_corpusId = 1;
        public static final int Corpus_corpusVersion = 2;
        public static final int Corpus_documentMaxAgeSecs = 3;
        public static final int Corpus_perAccountTemplate = 4;
        public static final int Corpus_schemaOrgType = 5;
        public static final int Corpus_semanticallySearchable = 6;
        public static final int Corpus_trimmable = 7;
        public static final int[] FeatureParam = {com.medscape.android.R.attr.paramName, com.medscape.android.R.attr.paramValue};
        public static final int FeatureParam_paramName = 0;
        public static final int FeatureParam_paramValue = 1;
        public static final int[] GlobalSearch = {com.medscape.android.R.attr.defaultIntentAction, com.medscape.android.R.attr.defaultIntentActivity, com.medscape.android.R.attr.defaultIntentData, com.medscape.android.R.attr.searchEnabled, com.medscape.android.R.attr.searchLabel, com.medscape.android.R.attr.settingsDescription};
        public static final int[] GlobalSearchCorpus = {com.medscape.android.R.attr.allowShortcuts};
        public static final int GlobalSearchCorpus_allowShortcuts = 0;
        public static final int[] GlobalSearchSection = {com.medscape.android.R.attr.sectionContent, com.medscape.android.R.attr.sectionType};
        public static final int GlobalSearchSection_sectionContent = 0;
        public static final int GlobalSearchSection_sectionType = 1;
        public static final int GlobalSearch_defaultIntentAction = 0;
        public static final int GlobalSearch_defaultIntentActivity = 1;
        public static final int GlobalSearch_defaultIntentData = 2;
        public static final int GlobalSearch_searchEnabled = 3;
        public static final int GlobalSearch_searchLabel = 4;
        public static final int GlobalSearch_settingsDescription = 5;
        public static final int[] IMECorpus = {com.medscape.android.R.attr.inputEnabled, com.medscape.android.R.attr.sourceClass, com.medscape.android.R.attr.toAddressesSection, com.medscape.android.R.attr.userInputSection, com.medscape.android.R.attr.userInputTag, com.medscape.android.R.attr.userInputValue};
        public static final int IMECorpus_inputEnabled = 0;
        public static final int IMECorpus_sourceClass = 1;
        public static final int IMECorpus_toAddressesSection = 2;
        public static final int IMECorpus_userInputSection = 3;
        public static final int IMECorpus_userInputTag = 4;
        public static final int IMECorpus_userInputValue = 5;
        public static final int[] Section = {com.medscape.android.R.attr.indexPrefixes, com.medscape.android.R.attr.noIndex, com.medscape.android.R.attr.schemaOrgProperty, com.medscape.android.R.attr.sectionFormat, com.medscape.android.R.attr.sectionId, com.medscape.android.R.attr.sectionWeight, com.medscape.android.R.attr.subsectionSeparator};
        public static final int[] SectionFeature = {com.medscape.android.R.attr.featureType};
        public static final int SectionFeature_featureType = 0;
        public static final int Section_indexPrefixes = 0;
        public static final int Section_noIndex = 1;
        public static final int Section_schemaOrgProperty = 2;
        public static final int Section_sectionFormat = 3;
        public static final int Section_sectionId = 4;
        public static final int Section_sectionWeight = 5;
        public static final int Section_subsectionSeparator = 6;

        private styleable() {
        }
    }

    private R() {
    }
}
