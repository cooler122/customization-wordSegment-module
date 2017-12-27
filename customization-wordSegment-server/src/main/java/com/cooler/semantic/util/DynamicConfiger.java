package com.cooler.semantic.util;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.collection.trie.bintrie.BinTrie;
import com.hankcs.hanlp.corpus.tag.Nature;
import com.hankcs.hanlp.dictionary.CoreDictionary;
import com.hankcs.hanlp.dictionary.CustomDictionary;
import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.List;

@Slf4j
public class DynamicConfiger {

    public static boolean deleteDynamicTrie(String name) {
        if (StringUtils.isBlank(name)) return false;
        CustomDictionary.mTrie.remove(name);

        File file = new File(HanLP.Config.DynamicPath + name);
        if (file.isFile() && file.exists()) {
            file.delete();
        }
        return true;
    }

    public static boolean addDynamicWord(String name, String term) {
        if (!StringUtils.isNoneBlank(name, term)) return false;

        BinTrie trie = getBinTrieByName(name, true);
        boolean ret;
        if ((ret = addTerm2BinTrie(trie, term))) {
            saveDynamicDict(name);
        }
        return ret;
    }

    public static boolean deleteDynamicWord(String name, String word) {
        if (!StringUtils.isNoneBlank(name, word)) return false;
        BinTrie trie = CustomDictionary.mTrie.get(name);
        boolean ret;
        if ((ret = delWordInBinTrie(trie, word))) {
            saveDynamicDict(name);
        }
        return ret;
    }

    public static boolean addDynamicWords(String name, List<String> terms) {
        if (StringUtils.isBlank(name) || terms == null) return false;

        BinTrie trie = getBinTrieByName(name, true);
        for (String term : terms) {
            addTerm2BinTrie(trie, term);
        }
        saveDynamicDict(name);
        return true;
    }

    public static boolean deleteDynamicWords(String name, List<String> words) {
        if (StringUtils.isBlank(name) || words == null) return false;
        BinTrie trie = getBinTrieByName(name, false);
        boolean ret = false;
        for (String word : words) {
            if (delWordInBinTrie(trie, word)) ret = true;
        }
        if (ret) saveDynamicDict(name);
        return ret;
    }

    public static boolean alterDynamicWord(String name, String term) {
        if (!StringUtils.isNoneBlank(name, term)) return false;
        BinTrie trie = getBinTrieByName(name, false);
        if (trie == null) return false;

        String[] wParts = term.split("\\s");
        if (wParts.length == 3 && Nature.containsNature(wParts[1])) {
            trie.set(wParts[0], new CoreDictionary.Attribute(Nature.fromString(wParts[1]), Integer.parseInt(wParts[2])));
        } else {
            return false;
        }
        saveDynamicDict(name);
        return true;
    }

    public static boolean containDynamicWord(String name, String word) {
        if (!StringUtils.isNoneBlank(name, word)) return false;
        BinTrie trie = getBinTrieByName(name, false);
        return trie != null && trie.containsKey(word);
    }

    @Synchronized
    public static boolean saveDynamicDict(String name) {
        return CustomDictionary.saveDynamicDict(name);
    }

    @Synchronized
    public static boolean loadDynamicDict(String name) {
        return CustomDictionary.loadDynamicDict(name);
    }

    /**
     * 获取bintrie.
     *
     * @param name   the name
     * @param create 不存在，是否新建
     * @return bin trie by name
     */
    private static BinTrie getBinTrieByName(String name, boolean create) {
        BinTrie trie = CustomDictionary.mTrie.get(name);
        if (create && trie == null) {
            trie = new BinTrie();
            CustomDictionary.mTrie.put(name, trie);
        }
        return trie;
    }

    /**
     * Add term 2 bin trie boolean.
     *
     * @param trie the trie
     * @param term the term
     * @return the boolean
     */
    static boolean addTerm2BinTrie(BinTrie trie, String term) {
        if (trie == null || StringUtils.isBlank(term)) return false;
        String[] wParts = term.split("\\s");
        if (trie.containsKey(wParts[0])) {
            return false;
        }
        if (wParts.length > 1 && Nature.containsNature(wParts[1])) {
            trie.put(wParts[0], new CoreDictionary.Attribute(Nature.fromString(wParts[1]), 100));
        } else {
            trie.put(wParts[0], new CoreDictionary.Attribute(Nature.n_other, 100));
        }
        return true;
    }

    static boolean delWordInBinTrie(BinTrie trie, String word) {
        if (trie == null || StringUtils.isBlank(word)) return false;
        if (trie.containsKey(word)) {
            trie.remove(word);
            return true;
        }
        return false;
    }
}
