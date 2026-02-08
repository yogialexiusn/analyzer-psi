//package com.example.converteraudio.util;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//
//public class ParserOpenAiUtilTest {
//
//    @Test
//    public void testParserOpenAi() {
//        ObjectMapper mapper = new ObjectMapper();
//        String raw = "{\n" +
//                "    \"output\": {\n" +
//                "        \"id\": \"resp_0d31061fedbcddc1006987389f1b9c8196ab26692a32d44057\",\n" +
//                "        \"created_at\": 1.770469535E9,\n" +
//                "        \"error\": null,\n" +
//                "        \"incomplete_details\": null,\n" +
//                "        \"instructions\": null,\n" +
//                "        \"metadata\": {},\n" +
//                "        \"model\": \"gpt-5-nano-2025-08-07\",\n" +
//                "        \"object\": \"response\",\n" +
//                "        \"output\": [\n" +
//                "            {\n" +
//                "                \"id\": \"rs_0d31061fedbcddc1006987389f5e3c8196845ba3d82648e64a\",\n" +
//                "                \"summary\": [],\n" +
//                "                \"type\": \"reasoning\",\n" +
//                "                \"isValid\": true\n" +
//                "            },\n" +
//                "            {\n" +
//                "                \"id\": \"msg_0d31061fedbcddc100698738af106881969c904e22f0dca7a1\",\n" +
//                "                \"content\": [\n" +
//                "                    {\n" +
//                "                        \"annotations\": [],\n" +
//                "                        \"text\": \"{\\n  \\\"strength\\\": [\\n    \\\"Lokasi strategis dekat area perkantoran/ kampus\\\",\\n    \\\"Kualitas kopi konsisten dengan variasi menu specialty\\\",\\n    \\\"Harga kompetitif dibanding kedai kopi sejenis\\\",\\n    \\\"Layanan cepat dengan sistem POS/order online\\\",\\n    \\\"Suasana nyaman, fasilitas wifi gratis, dan area duduk yang nyaman\\\"\\n  ],\\n  \\\"opportunity\\\": [\\n    \\\"Pertumbuhan minat kopi specialty di wilayah target\\\",\\n    \\\"Peluang kemitraan dengan roaster lokal dan pemasok biji kopi berkualitas\\\",\\n    \\\"Ekspansi layanan antar (delivery) dan kerja sama platform,\\n      meningkatkan jangkauan pelanggan\\\",\\n    \\\"Diversifikasi menu (makanan ringan, kue) untuk meningkatkan average order value\\\",\\n    \\\"Event komunitas, promo kampus/ kantor untuk meningkatkan brand awareness\\\"\\n  ],\\n  \\\"weakness\\\": [\\n    \\\"Kapasitas tempat duduk terbatas dan ruang kerja yang sempit\\\",\\n    \\\"Brand awareness masih rendah\\\",\\n    \\\"Ketergantungan pada pasokan biji kopi tertentu\\\",\\n    \\\"Margin keuntungan yang relatif tipis karena biaya operasional\\\",\\n    \\\"Kurangnya sistem operasional/inventori yang terstruktur\\\"\\n  ],\\n  \\\"threat\\\": [\\n    \\\"Persaingan ketat dari kedai kopi lain maupun jaringan/franchise\\\",\\n    \\\"Fluktuasi harga biji kopi dan biaya bahan baku\\\",\\n    \\\"Kenaikan biaya sewa lokasi/operasional\\\",\\n    \\\"Perubahan tren konsumen atau preferensi menu kopi\\\",\\n    \\\"Gangguan rantai pasokan dan faktor ekonomi makro\\\"\\n  ]\\n}\",\n" +
//                "                        \"type\": \"output_text\",\n" +
//                "                        \"logprobs\": [],\n" +
//                "                        \"isValid\": true\n" +
//                "                    }\n" +
//                "                ],\n" +
//                "                \"role\": \"assistant\",\n" +
//                "                \"status\": \"completed\",\n" +
//                "                \"type\": \"message\",\n" +
//                "                \"isValid\": true\n" +
//                "            }\n" +
//                "        ],\n" +
//                "        \"parallel_tool_calls\": true,\n" +
//                "        \"temperature\": 1.0,\n" +
//                "        \"tool_choice\": \"auto\",\n" +
//                "        \"tools\": [],\n" +
//                "        \"top_p\": 1.0,\n" +
//                "        \"background\": false,\n" +
//                "        \"max_output_tokens\": null,\n" +
//                "        \"max_tool_calls\": null,\n" +
//                "        \"previous_response_id\": null,\n" +
//                "        \"prompt_cache_key\": null,\n" +
//                "        \"reasoning\": {\n" +
//                "            \"effort\": \"medium\",\n" +
//                "            \"summary\": null,\n" +
//                "            \"isValid\": true\n" +
//                "        },\n" +
//                "        \"safety_identifier\": null,\n" +
//                "        \"service_tier\": \"default\",\n" +
//                "        \"status\": \"completed\",\n" +
//                "        \"text\": {\n" +
//                "            \"format\": {\n" +
//                "                \"type\": \"text\",\n" +
//                "                \"isValid\": true\n" +
//                "            },\n" +
//                "            \"verbosity\": \"medium\",\n" +
//                "            \"isValid\": true\n" +
//                "        },\n" +
//                "        \"top_logprobs\": 0,\n" +
//                "        \"truncation\": \"disabled\",\n" +
//                "        \"usage\": {\n" +
//                "            \"input_tokens\": 31,\n" +
//                "            \"input_tokens_details\": {\n" +
//                "                \"cached_tokens\": 0,\n" +
//                "                \"isValid\": true\n" +
//                "            },\n" +
//                "            \"output_tokens\": 2362,\n" +
//                "            \"output_tokens_details\": {\n" +
//                "                \"reasoning_tokens\": 1984,\n" +
//                "                \"isValid\": true\n" +
//                "            },\n" +
//                "            \"total_tokens\": 2393,\n" +
//                "            \"isValid\": true\n" +
//                "        },\n" +
//                "        \"user\": null,\n" +
//                "        \"isValid\": true,\n" +
//                "        \"store\": true,\n" +
//                "        \"prompt_cache_retention\": null,\n" +
//                "        \"presence_penalty\": 0.0,\n" +
//                "        \"frequency_penalty\": 0.0,\n" +
//                "        \"completed_at\": 1770469554,\n" +
//                "        \"billing\": {\n" +
//                "            \"payer\": \"developer\"\n" +
//                "        }\n" +
//                "    }\n" +
//                "}";
//        String out = ParserOpenAiUtil.getTextOriginal(raw, mapper);
//        assertNotNull(out);
//    }
//
//}
