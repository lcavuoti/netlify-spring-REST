package punchclock.service;

import java.util.List;

import org.springframework.stereotype.Service;

import punchclock.domain.Entry;
import punchclock.repository.EntryRepository;

@Service
public class EntryService {
	private EntryRepository entryRepository;

	public EntryService(EntryRepository entryRepository) {
		this.entryRepository = entryRepository;
	}

	public Entry createEntry(Entry entry) {
		return entryRepository.saveAndFlush(entry);
	}

//    public Entry createEntryById(Long id) {
//        return entryRepository.findById(id);
////        		.orElseThrow();
//    }

	public List<Entry> findAllEntries() {
		return entryRepository.findAll();
	}

	public Entry findEntryById(Long id) {
		return entryRepository.findById(id).get();
	}

	public Entry changeEntryById(Entry newEntry, Long id) {
		return entryRepository.findById(id).map(entry -> {
			entry.setCheckIn(newEntry.getCheckIn());
			entry.setCheckOut(newEntry.getCheckOut());

			return entryRepository.save(entry);
		}).orElseGet(() -> {
			newEntry.setId(id);
			return entryRepository.save(newEntry);
		});

	}

	public void removeEntryById(Long id) {
		entryRepository.deleteById(id);
	}
}
