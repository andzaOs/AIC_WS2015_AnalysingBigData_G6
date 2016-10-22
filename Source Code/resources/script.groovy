def String rootTopic;
def topics = [];
def subTopics = [][];

def String subItemWord = '';
def subItems = [];

def topicWords = [];

def lines = [];

new File("C:\\Users\\vanjalee\\Desktop\\topics.csv").splitEachLine(";") { fields ->

    def topicName0 = fields[0];
    if (rootTopic == null) {
        rootTopic = topicName0;
        topics.add(rootTopic);
        subTopics.add(rootTopic);
        subItems = [];   
    } else if (!rootTopic.equals(topicName0)) {
        subTopics[subTopics.size() - 1] += subItems;
        topicWords.add(subItemWord);
        rootTopic = topicName0;
        topics.add(rootTopic);
        subTopics.add(rootTopic);
        subItems = [];
        subItemWord = '';
    }    
    
    def topicName1 = fields[1];   
    def topicName2 = fields[2];
    def topicName3 = fields[3];
    def topicName4 = fields[4];
    def topicName5 = fields[5];
    
    if (topicName1 != null && !subItems.contains(topicName1)) {
        subItems.add(topicName1);
        subItemWord += topicName1 + ', ';
    }
    if (topicName2 != null && !subItems.contains(topicName2)) {
        subItems.add(topicName2);
        subItemWord += topicName2 + ', ';
    }
    if (topicName3 != null && !subItems.contains(topicName3)) {
        subItems.add(topicName3);
        subItemWord += topicName3 + ', ';
    }
    if (topicName4 != null && !subItems.contains(topicName4)) {
        subItems.add(topicName4);
        subItemWord += topicName4 + ', ';
    }
    if (topicName5 != null && !subItems.contains(topicName5)) {
        subItems.add(topicName5);
        subItemWord += topicName5 + ', ';
    }
}
subTopics[subTopics.size() - 1] += subItems;
topicWords.add(subItemWord);


lines.add("[");
for (int i = 0; i < subTopics.size(); i++) {
    lines.add("{");
    lines.add('\"name\": ' + '\"' + topics[i] + '\",');    
    lines.add('\"words\": ' + '\"' + topicWords[i] + '\"');
    if (subTopics.size() - 1 > i) {
         lines.add("},");
    } else {
         lines.add("}");
    }
}
lines.add("]");

new File("C:\\Users\\vanjalee\\Desktop\\topics.json").withWriter { out ->
    lines.each {
        out.println it
    }
}