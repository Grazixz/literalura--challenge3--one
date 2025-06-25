package br.com.one.literalura__challenge3__one.DTO;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DataBook( String title,
                        List<DataAuthor> authors,
                        List<String> summaries,
                        List<String> languages,
                        @JsonAlias("download_count") int downloadCount) {
}
