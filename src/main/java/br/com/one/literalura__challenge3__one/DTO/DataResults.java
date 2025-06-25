package br.com.one.literalura__challenge3__one.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DataResults(List<DataBook> results,
                          int count) {
}
